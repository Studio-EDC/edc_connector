/*
 *  Copyright (c) 2026
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 */

package org.eclipse.edc.sample.runtime;

import com.fasterxml.jackson.core.type.TypeReference;
import org.eclipse.edc.jwt.signer.spi.JwsSignerProvider;
import org.eclipse.edc.runtime.metamodel.annotation.Extension;
import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.runtime.metamodel.annotation.Provider;
import org.eclipse.edc.runtime.metamodel.annotation.Provides;
import org.eclipse.edc.runtime.metamodel.annotation.Setting;
import org.eclipse.edc.spi.iam.AudienceResolver;
import org.eclipse.edc.spi.iam.ClaimToken;
import org.eclipse.edc.spi.iam.IdentityService;
import org.eclipse.edc.spi.iam.TokenParameters;
import org.eclipse.edc.spi.iam.TokenRepresentation;
import org.eclipse.edc.spi.iam.VerificationContext;
import org.eclipse.edc.spi.monitor.Monitor;
import org.eclipse.edc.spi.result.Result;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;
import org.eclipse.edc.spi.types.TypeManager;
import org.eclipse.edc.token.JwtGenerationService;
import org.eclipse.edc.token.spi.TokenDecorator;

import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Provides(IdentityService.class)
@Extension(value = BearerJwtIdentityExtension.NAME)
public class BearerJwtIdentityExtension implements ServiceExtension {

    public static final String NAME = "Bearer JWT Identity";
    private static final TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<>() { };
    private static final long DEFAULT_TOKEN_EXPIRATION_SECONDS = 300L;
    private static final String DEFAULT_PRIVATE_KEY_ALIAS = "shared-private-key";
    private static final String DEFAULT_PUBLIC_KEY_ID = "shared-key";

    @Inject
    private TypeManager typeManager;

    @Inject
    private JwsSignerProvider signerProvider;

    @Inject
    private Clock clock;

    @Inject
    private Monitor monitor;

    @Setting(key = "edc.iam.sts.privatekey.alias", defaultValue = DEFAULT_PRIVATE_KEY_ALIAS)
    private String privateKeyAlias;

    @Setting(key = "edc.iam.sts.publickey.id", defaultValue = DEFAULT_PUBLIC_KEY_ID)
    private String publicKeyId;

    @Setting(key = "edc.iam.token.expiration.seconds", defaultValue = "" + DEFAULT_TOKEN_EXPIRATION_SECONDS)
    private long tokenExpirationSeconds;

    @Override
    public void initialize(ServiceExtensionContext context) {
        context.getMonitor().warning("Using transitional Bearer JWT identity provider for managed connectors. Incoming Bearer tokens are parsed without signature verification.");
    }

    @Provider
    public IdentityService identityService(ServiceExtensionContext context) {
        return new BearerJwtIdentityService(
                context.getParticipantId(),
                privateKeyAlias,
                publicKeyId,
                tokenExpirationSeconds,
                clock,
                typeManager,
                signerProvider,
                monitor
        );
    }

    @Provider
    public AudienceResolver audienceResolver() {
        return message -> Result.success(message.getCounterPartyAddress());
    }

    private static class BearerJwtIdentityService implements IdentityService {
        private final String participantId;
        private final String privateKeyAlias;
        private final String publicKeyId;
        private final long tokenExpirationSeconds;
        private final Clock clock;
        private final TypeManager typeManager;
        private final JwsSignerProvider signerProvider;
        private final Monitor monitor;

        private BearerJwtIdentityService(String participantId, String privateKeyAlias, String publicKeyId, long tokenExpirationSeconds,
                                         Clock clock, TypeManager typeManager, JwsSignerProvider signerProvider, Monitor monitor) {
            this.participantId = participantId;
            this.privateKeyAlias = privateKeyAlias;
            this.publicKeyId = publicKeyId;
            this.tokenExpirationSeconds = tokenExpirationSeconds;
            this.clock = clock;
            this.typeManager = typeManager;
            this.signerProvider = signerProvider;
            this.monitor = monitor;
        }

        @Override
        public Result<TokenRepresentation> obtainClientCredentials(TokenParameters parameters) {
            var now = Instant.now(clock);
            TokenDecorator decorator = builder -> {
                builder.headers(parameters.getHeaders());
                parameters.getClaims().forEach(builder::claims);
                builder.header("kid", publicKeyId);

                if (parameters.getStringClaim("iss") == null) {
                    builder.claims("iss", participantId);
                }
                if (parameters.getStringClaim("sub") == null) {
                    builder.claims("sub", participantId);
                }
                if (parameters.getStringClaim("client_id") == null) {
                    builder.claims("client_id", participantId);
                }
                if (!parameters.getClaims().containsKey("iat")) {
                    builder.claims("iat", Date.from(now));
                }
                if (!parameters.getClaims().containsKey("nbf")) {
                    builder.claims("nbf", Date.from(now));
                }
                if (!parameters.getClaims().containsKey("exp")) {
                    builder.claims("exp", Date.from(now.plusSeconds(tokenExpirationSeconds)));
                }
                if (!parameters.getClaims().containsKey("jti")) {
                    builder.claims("jti", UUID.randomUUID().toString());
                }
                return builder;
            };

            return new JwtGenerationService(signerProvider).generate(privateKeyAlias, decorator);
        }

        @Override
        public Result<ClaimToken> verifyJwtToken(TokenRepresentation tokenRepresentation, VerificationContext context) {
            var rawToken = tokenRepresentation.getToken();
            if (rawToken == null || rawToken.isBlank()) {
                return Result.failure("Token is empty");
            }

            var token = normalizeToken(rawToken);
            var parts = token.split("\\.");
            if (parts.length < 2) {
                return Result.failure("Token is not a JWT");
            }

            try {
                var payload = new String(Base64.getUrlDecoder().decode(parts[1]), StandardCharsets.UTF_8);
                Map<String, Object> claims = typeManager.getMapper().readValue(payload, MAP_TYPE);

                if (isExpired(claims)) {
                    return Result.failure("Token expired");
                }

                var builder = ClaimToken.Builder.newInstance().claims(claims);
                if (!claims.containsKey("client_id")) {
                    var identity = firstNonBlank(claims.get("sub"), claims.get("iss"));
                    if (identity != null) {
                        builder.claim("client_id", identity);
                    }
                }

                return Result.success(builder.build());
            } catch (Exception ex) {
                monitor.debug(() -> "Failed to parse Bearer token: " + ex.getMessage());
                return Result.failure("Failed to parse Bearer token");
            }
        }

        private boolean isExpired(Map<String, Object> claims) {
            var exp = claims.get("exp");
            if (exp instanceof Number number) {
                return Instant.ofEpochSecond(number.longValue()).isBefore(Instant.now(clock));
            }
            return false;
        }

        private String normalizeToken(String token) {
            return token.regionMatches(true, 0, "Bearer ", 0, 7) ? token.substring(7).trim() : token.trim();
        }

        private String firstNonBlank(Object... values) {
            for (var value : values) {
                if (value instanceof String stringValue && !stringValue.isBlank()) {
                    return stringValue;
                }
            }
            return null;
        }
    }
}

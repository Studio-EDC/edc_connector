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

import org.eclipse.edc.runtime.metamodel.annotation.Inject;
import org.eclipse.edc.spi.security.Vault;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;
import org.eclipse.edc.util.string.StringUtils;

/**
 * Seeds demo STS signing keys for DCP-enabled runtimes that rely on the in-memory vault.
 */
public class SeedStsKeysExtension implements ServiceExtension {
    private static final String STS_PRIVATE_KEY_ALIAS = "edc.iam.sts.privatekey.alias";
    private static final String STS_PUBLIC_KEY_ID = "edc.iam.sts.publickey.id";
    private static final String DEFAULT_PRIVATE_KEY_ALIAS = "shared-private-key";
    private static final String DEFAULT_PUBLIC_KEY_ID = "shared-key";

    @Inject
    private Vault vault;

    @Override
    public void initialize(ServiceExtensionContext context) {
        var privateKeyAlias = context.getSetting(STS_PRIVATE_KEY_ALIAS, DEFAULT_PRIVATE_KEY_ALIAS);
        var publicKeyId = context.getSetting(STS_PUBLIC_KEY_ID, DEFAULT_PUBLIC_KEY_ID);

        if (!StringUtils.isNullOrEmpty(vault.resolveSecret(privateKeyAlias))
                && !StringUtils.isNullOrEmpty(vault.resolveSecret(publicKeyId))) {
            return;
        }

        var publicKey = """
                -----BEGIN PUBLIC KEY-----
                MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE1l0Lof0a1yBc8KXhesAnoBvxZw5r
                oYnkAXuqCYfNK3ex+hMWFuiXGUxHlzShAehR6wvwzV23bbC0tcFcVgW//A==
                -----END PUBLIC KEY-----
                """;

        var privateKey = """
                -----BEGIN EC PRIVATE KEY-----
                MHcCAQEEIARDUGJgKy1yzxkueIJ1k3MPUWQ/tbQWQNqW6TjyHpdcoAoGCCqGSM49
                AwEHoUQDQgAE1l0Lof0a1yBc8KXhesAnoBvxZw5roYnkAXuqCYfNK3ex+hMWFuiX
                GUxHlzShAehR6wvwzV23bbC0tcFcVgW//A==
                -----END EC PRIVATE KEY-----
                """;

        vault.storeSecret(privateKeyAlias, privateKey);
        vault.storeSecret(publicKeyId, publicKey);

        context.getMonitor().warning("Seeded demo STS keys into the connector vault for DCP token handling.");
    }
}

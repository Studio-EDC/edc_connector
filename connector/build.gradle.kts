/*
 *  Copyright (c) 2022 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *
 */

plugins {
    `java-library`
    id("application")
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(libs.edc.control.api.configuration)
    implementation(libs.edc.control.plane.api.client)
    implementation(libs.edc.control.plane.api)
    implementation(libs.edc.control.plane.core)
    implementation(libs.edc.token.core)
    implementation(libs.edc.token.lib)
    implementation(libs.edc.dsp)
    implementation(libs.edc.identity.trust.service)
    implementation(libs.edc.identity.did.core)
    implementation(libs.edc.identity.did.web)
    implementation(libs.edc.dcp.core)
    implementation(libs.edc.oauth2.client)
    implementation(libs.edc.sts.core)
    implementation(libs.edc.sts.api)
    implementation(libs.edc.sts.account.service.local)
    implementation(libs.edc.http)
    implementation(libs.edc.configuration.filesystem)
    implementation(libs.edc.management.api)
    implementation(libs.edc.transfer.data.plane.signaling)
    implementation(libs.edc.validator.data.address.http.data)

    implementation(libs.edc.edr.cache.api)
    implementation(libs.edc.edr.store.core)
    implementation(libs.edc.edr.store.receiver)

    implementation(libs.edc.data.plane.selector.api)
    implementation(libs.edc.data.plane.selector.core)

    implementation(libs.edc.data.plane.self.registration)
    implementation(libs.edc.data.plane.signaling.api)
    implementation(libs.edc.data.plane.core)
    implementation(libs.edc.data.plane.http)
    implementation(libs.edc.data.plane.iam)

    implementation(libs.edc.management.api.asset)
    implementation(libs.edc.management.api.catalog)
    implementation(libs.edc.management.api.contract.agreement)
    implementation(libs.edc.management.api.contract.definition)
    implementation(libs.edc.management.api.contract.negotiation)
    implementation(libs.edc.management.api.policy.definition)
    implementation(libs.edc.management.api.transfer.process)
    implementation(libs.edc.management.api.protocol.version)

    implementation(libs.edc.transaction.local)

    implementation("org.postgresql:postgresql:42.7.3")
    implementation(libs.edc.sql.core)
    implementation(libs.edc.sql.pool.apache.commons)
    implementation(libs.edc.asset.index.sql)
    implementation(libs.edc.contract.definition.store.sql)
    implementation(libs.edc.contract.negotiation.store.sql)
    implementation(libs.edc.control.plane.sql)
    implementation(libs.edc.policy.definition.store.sql)
    implementation(libs.edc.transfer.process.store.sql)

    implementation(libs.edc.token.core)
    implementation(libs.edc.auth.tokenbased)
    implementation(libs.edc.auth.spi)
    implementation(libs.edc.auth.delegated)
    implementation(libs.edc.auth.configuration)

    implementation(libs.edc.data.plane.spi)
    implementation(libs.edc.web.spi)
    implementation(libs.edc.jwt.signer.spi)
    implementation(libs.edc.keys.spi)

}

application {
    mainClass.set("$group.boot.system.runtime.BaseRuntime")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    mergeServiceFiles()
    archiveFileName.set("connector.jar")
}

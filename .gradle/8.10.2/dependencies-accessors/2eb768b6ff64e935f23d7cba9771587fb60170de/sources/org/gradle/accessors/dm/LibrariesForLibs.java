package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the {@code libs} extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AzureLibraryAccessors laccForAzureLibraryAccessors = new AzureLibraryAccessors(owner);
    private final EdcLibraryAccessors laccForEdcLibraryAccessors = new EdcLibraryAccessors(owner);
    private final JakartaLibraryAccessors laccForJakartaLibraryAccessors = new JakartaLibraryAccessors(owner);
    private final KafkaLibraryAccessors laccForKafkaLibraryAccessors = new KafkaLibraryAccessors(owner);
    private final MinioLibraryAccessors laccForMinioLibraryAccessors = new MinioLibraryAccessors(owner);
    private final OkhttpLibraryAccessors laccForOkhttpLibraryAccessors = new OkhttpLibraryAccessors(owner);
    private final OpentelemetryLibraryAccessors laccForOpentelemetryLibraryAccessors = new OpentelemetryLibraryAccessors(owner);
    private final TestcontainersLibraryAccessors laccForTestcontainersLibraryAccessors = new TestcontainersLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

    /**
     * Dependency provider for <b>awaitility</b> with <b>org.awaitility:awaitility</b> coordinates and
     * with version reference <b>awaitility</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getAwaitility() {
        return create("awaitility");
    }

    /**
     * Dependency provider for <b>restAssured</b> with <b>io.rest-assured:rest-assured</b> coordinates and
     * with version reference <b>restAssured</b>
     * <p>
     * This dependency was declared in catalog libs.versions.toml
     */
    public Provider<MinimalExternalModuleDependency> getRestAssured() {
        return create("restAssured");
    }

    /**
     * Group of libraries at <b>azure</b>
     */
    public AzureLibraryAccessors getAzure() {
        return laccForAzureLibraryAccessors;
    }

    /**
     * Group of libraries at <b>edc</b>
     */
    public EdcLibraryAccessors getEdc() {
        return laccForEdcLibraryAccessors;
    }

    /**
     * Group of libraries at <b>jakarta</b>
     */
    public JakartaLibraryAccessors getJakarta() {
        return laccForJakartaLibraryAccessors;
    }

    /**
     * Group of libraries at <b>kafka</b>
     */
    public KafkaLibraryAccessors getKafka() {
        return laccForKafkaLibraryAccessors;
    }

    /**
     * Group of libraries at <b>minio</b>
     */
    public MinioLibraryAccessors getMinio() {
        return laccForMinioLibraryAccessors;
    }

    /**
     * Group of libraries at <b>okhttp</b>
     */
    public OkhttpLibraryAccessors getOkhttp() {
        return laccForOkhttpLibraryAccessors;
    }

    /**
     * Group of libraries at <b>opentelemetry</b>
     */
    public OpentelemetryLibraryAccessors getOpentelemetry() {
        return laccForOpentelemetryLibraryAccessors;
    }

    /**
     * Group of libraries at <b>testcontainers</b>
     */
    public TestcontainersLibraryAccessors getTestcontainers() {
        return laccForTestcontainersLibraryAccessors;
    }

    /**
     * Group of versions at <b>versions</b>
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Group of bundles at <b>bundles</b>
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Group of plugins at <b>plugins</b>
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class AzureLibraryAccessors extends SubDependencyFactory {
        private final AzureStorageLibraryAccessors laccForAzureStorageLibraryAccessors = new AzureStorageLibraryAccessors(owner);

        public AzureLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>azure.storage</b>
         */
        public AzureStorageLibraryAccessors getStorage() {
            return laccForAzureStorageLibraryAccessors;
        }

    }

    public static class AzureStorageLibraryAccessors extends SubDependencyFactory {

        public AzureStorageLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>blob</b> with <b>com.azure:azure-storage-blob</b> coordinates and
         * with version <b>12.29.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBlob() {
            return create("azure.storage.blob");
        }

    }

    public static class EdcLibraryAccessors extends SubDependencyFactory {
        private final EdcApiLibraryAccessors laccForEdcApiLibraryAccessors = new EdcApiLibraryAccessors(owner);
        private final EdcAuthLibraryAccessors laccForEdcAuthLibraryAccessors = new EdcAuthLibraryAccessors(owner);
        private final EdcBomLibraryAccessors laccForEdcBomLibraryAccessors = new EdcBomLibraryAccessors(owner);
        private final EdcBuildLibraryAccessors laccForEdcBuildLibraryAccessors = new EdcBuildLibraryAccessors(owner);
        private final EdcConfigurationLibraryAccessors laccForEdcConfigurationLibraryAccessors = new EdcConfigurationLibraryAccessors(owner);
        private final EdcConnectorLibraryAccessors laccForEdcConnectorLibraryAccessors = new EdcConnectorLibraryAccessors(owner);
        private final EdcControlLibraryAccessors laccForEdcControlLibraryAccessors = new EdcControlLibraryAccessors(owner);
        private final EdcDataLibraryAccessors laccForEdcDataLibraryAccessors = new EdcDataLibraryAccessors(owner);
        private final EdcEdrLibraryAccessors laccForEdcEdrLibraryAccessors = new EdcEdrLibraryAccessors(owner);
        private final EdcFcLibraryAccessors laccForEdcFcLibraryAccessors = new EdcFcLibraryAccessors(owner);
        private final EdcIamLibraryAccessors laccForEdcIamLibraryAccessors = new EdcIamLibraryAccessors(owner);
        private final EdcJsonLibraryAccessors laccForEdcJsonLibraryAccessors = new EdcJsonLibraryAccessors(owner);
        private final EdcManagementLibraryAccessors laccForEdcManagementLibraryAccessors = new EdcManagementLibraryAccessors(owner);
        private final EdcMonitorLibraryAccessors laccForEdcMonitorLibraryAccessors = new EdcMonitorLibraryAccessors(owner);
        private final EdcTokenLibraryAccessors laccForEdcTokenLibraryAccessors = new EdcTokenLibraryAccessors(owner);
        private final EdcTransferLibraryAccessors laccForEdcTransferLibraryAccessors = new EdcTransferLibraryAccessors(owner);
        private final EdcValidatorLibraryAccessors laccForEdcValidatorLibraryAccessors = new EdcValidatorLibraryAccessors(owner);
        private final EdcVaultLibraryAccessors laccForEdcVaultLibraryAccessors = new EdcVaultLibraryAccessors(owner);
        private final EdcWebLibraryAccessors laccForEdcWebLibraryAccessors = new EdcWebLibraryAccessors(owner);

        public EdcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>boot</b> with <b>org.eclipse.edc:boot</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBoot() {
            return create("edc.boot");
        }

        /**
         * Dependency provider for <b>dsp</b> with <b>org.eclipse.edc:dsp</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDsp() {
            return create("edc.dsp");
        }

        /**
         * Dependency provider for <b>http</b> with <b>org.eclipse.edc:http</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHttp() {
            return create("edc.http");
        }

        /**
         * Dependency provider for <b>junit</b> with <b>org.eclipse.edc:junit</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() {
            return create("edc.junit");
        }

        /**
         * Group of libraries at <b>edc.api</b>
         */
        public EdcApiLibraryAccessors getApi() {
            return laccForEdcApiLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.auth</b>
         */
        public EdcAuthLibraryAccessors getAuth() {
            return laccForEdcAuthLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.bom</b>
         */
        public EdcBomLibraryAccessors getBom() {
            return laccForEdcBomLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.build</b>
         */
        public EdcBuildLibraryAccessors getBuild() {
            return laccForEdcBuildLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.configuration</b>
         */
        public EdcConfigurationLibraryAccessors getConfiguration() {
            return laccForEdcConfigurationLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.connector</b>
         */
        public EdcConnectorLibraryAccessors getConnector() {
            return laccForEdcConnectorLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.control</b>
         */
        public EdcControlLibraryAccessors getControl() {
            return laccForEdcControlLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.data</b>
         */
        public EdcDataLibraryAccessors getData() {
            return laccForEdcDataLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.edr</b>
         */
        public EdcEdrLibraryAccessors getEdr() {
            return laccForEdcEdrLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.fc</b>
         */
        public EdcFcLibraryAccessors getFc() {
            return laccForEdcFcLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.iam</b>
         */
        public EdcIamLibraryAccessors getIam() {
            return laccForEdcIamLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.json</b>
         */
        public EdcJsonLibraryAccessors getJson() {
            return laccForEdcJsonLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.management</b>
         */
        public EdcManagementLibraryAccessors getManagement() {
            return laccForEdcManagementLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.monitor</b>
         */
        public EdcMonitorLibraryAccessors getMonitor() {
            return laccForEdcMonitorLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.token</b>
         */
        public EdcTokenLibraryAccessors getToken() {
            return laccForEdcTokenLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.transfer</b>
         */
        public EdcTransferLibraryAccessors getTransfer() {
            return laccForEdcTransferLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.validator</b>
         */
        public EdcValidatorLibraryAccessors getValidator() {
            return laccForEdcValidatorLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.vault</b>
         */
        public EdcVaultLibraryAccessors getVault() {
            return laccForEdcVaultLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.web</b>
         */
        public EdcWebLibraryAccessors getWeb() {
            return laccForEdcWebLibraryAccessors;
        }

    }

    public static class EdcApiLibraryAccessors extends SubDependencyFactory {

        public EdcApiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>observability</b> with <b>org.eclipse.edc:api-observability</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getObservability() {
            return create("edc.api.observability");
        }

    }

    public static class EdcAuthLibraryAccessors extends SubDependencyFactory {

        public EdcAuthLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>tokenbased</b> with <b>org.eclipse.edc:auth-tokenbased</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getTokenbased() {
            return create("edc.auth.tokenbased");
        }

    }

    public static class EdcBomLibraryAccessors extends SubDependencyFactory {
        private final EdcBomControlplaneLibraryAccessors laccForEdcBomControlplaneLibraryAccessors = new EdcBomControlplaneLibraryAccessors(owner);
        private final EdcBomDataplaneLibraryAccessors laccForEdcBomDataplaneLibraryAccessors = new EdcBomDataplaneLibraryAccessors(owner);

        public EdcBomLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.bom.controlplane</b>
         */
        public EdcBomControlplaneLibraryAccessors getControlplane() {
            return laccForEdcBomControlplaneLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.bom.dataplane</b>
         */
        public EdcBomDataplaneLibraryAccessors getDataplane() {
            return laccForEdcBomDataplaneLibraryAccessors;
        }

    }

    public static class EdcBomControlplaneLibraryAccessors extends SubDependencyFactory {

        public EdcBomControlplaneLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>base</b> with <b>org.eclipse.edc:controlplane-base-bom</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBase() {
            return create("edc.bom.controlplane.base");
        }

    }

    public static class EdcBomDataplaneLibraryAccessors extends SubDependencyFactory {

        public EdcBomDataplaneLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>base</b> with <b>org.eclipse.edc:dataplane-base-bom</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getBase() {
            return create("edc.bom.dataplane.base");
        }

    }

    public static class EdcBuildLibraryAccessors extends SubDependencyFactory {

        public EdcBuildLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>plugin</b> with <b>org.eclipse.edc.edc-build:org.eclipse.edc.edc-build.gradle.plugin</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getPlugin() {
            return create("edc.build.plugin");
        }

    }

    public static class EdcConfigurationLibraryAccessors extends SubDependencyFactory {

        public EdcConfigurationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>filesystem</b> with <b>org.eclipse.edc:configuration-filesystem</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getFilesystem() {
            return create("edc.configuration.filesystem");
        }

    }

    public static class EdcConnectorLibraryAccessors extends SubDependencyFactory {

        public EdcConnectorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.eclipse.edc:connector-core</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("edc.connector.core");
        }

    }

    public static class EdcControlLibraryAccessors extends SubDependencyFactory {
        private final EdcControlApiLibraryAccessors laccForEdcControlApiLibraryAccessors = new EdcControlApiLibraryAccessors(owner);
        private final EdcControlPlaneLibraryAccessors laccForEdcControlPlaneLibraryAccessors = new EdcControlPlaneLibraryAccessors(owner);

        public EdcControlLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.control.api</b>
         */
        public EdcControlApiLibraryAccessors getApi() {
            return laccForEdcControlApiLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.control.plane</b>
         */
        public EdcControlPlaneLibraryAccessors getPlane() {
            return laccForEdcControlPlaneLibraryAccessors;
        }

    }

    public static class EdcControlApiLibraryAccessors extends SubDependencyFactory {

        public EdcControlApiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>configuration</b> with <b>org.eclipse.edc:control-api-configuration</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getConfiguration() {
            return create("edc.control.api.configuration");
        }

    }

    public static class EdcControlPlaneLibraryAccessors extends SubDependencyFactory {
        private final EdcControlPlaneApiLibraryAccessors laccForEdcControlPlaneApiLibraryAccessors = new EdcControlPlaneApiLibraryAccessors(owner);

        public EdcControlPlaneLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.eclipse.edc:control-plane-core</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("edc.control.plane.core");
        }

        /**
         * Dependency provider for <b>spi</b> with <b>org.eclipse.edc:control-plane-spi</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSpi() {
            return create("edc.control.plane.spi");
        }

        /**
         * Group of libraries at <b>edc.control.plane.api</b>
         */
        public EdcControlPlaneApiLibraryAccessors getApi() {
            return laccForEdcControlPlaneApiLibraryAccessors;
        }

    }

    public static class EdcControlPlaneApiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public EdcControlPlaneApiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.eclipse.edc:control-plane-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("edc.control.plane.api");
        }

        /**
         * Dependency provider for <b>client</b> with <b>org.eclipse.edc:control-plane-api-client</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getClient() {
            return create("edc.control.plane.api.client");
        }

    }

    public static class EdcDataLibraryAccessors extends SubDependencyFactory {
        private final EdcDataPlaneLibraryAccessors laccForEdcDataPlaneLibraryAccessors = new EdcDataPlaneLibraryAccessors(owner);

        public EdcDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.data.plane</b>
         */
        public EdcDataPlaneLibraryAccessors getPlane() {
            return laccForEdcDataPlaneLibraryAccessors;
        }

    }

    public static class EdcDataPlaneLibraryAccessors extends SubDependencyFactory {
        private final EdcDataPlaneAwsLibraryAccessors laccForEdcDataPlaneAwsLibraryAccessors = new EdcDataPlaneAwsLibraryAccessors(owner);
        private final EdcDataPlaneAzureLibraryAccessors laccForEdcDataPlaneAzureLibraryAccessors = new EdcDataPlaneAzureLibraryAccessors(owner);
        private final EdcDataPlanePublicLibraryAccessors laccForEdcDataPlanePublicLibraryAccessors = new EdcDataPlanePublicLibraryAccessors(owner);
        private final EdcDataPlaneSelectorLibraryAccessors laccForEdcDataPlaneSelectorLibraryAccessors = new EdcDataPlaneSelectorLibraryAccessors(owner);
        private final EdcDataPlaneSelfLibraryAccessors laccForEdcDataPlaneSelfLibraryAccessors = new EdcDataPlaneSelfLibraryAccessors(owner);
        private final EdcDataPlaneSignalingLibraryAccessors laccForEdcDataPlaneSignalingLibraryAccessors = new EdcDataPlaneSignalingLibraryAccessors(owner);

        public EdcDataPlaneLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.eclipse.edc:data-plane-core</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("edc.data.plane.core");
        }

        /**
         * Dependency provider for <b>http</b> with <b>org.eclipse.edc:data-plane-http</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHttp() {
            return create("edc.data.plane.http");
        }

        /**
         * Dependency provider for <b>iam</b> with <b>org.eclipse.edc:data-plane-iam</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getIam() {
            return create("edc.data.plane.iam");
        }

        /**
         * Dependency provider for <b>kafka</b> with <b>org.eclipse.edc:data-plane-kafka</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKafka() {
            return create("edc.data.plane.kafka");
        }

        /**
         * Dependency provider for <b>spi</b> with <b>org.eclipse.edc:data-plane-spi</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSpi() {
            return create("edc.data.plane.spi");
        }

        /**
         * Group of libraries at <b>edc.data.plane.aws</b>
         */
        public EdcDataPlaneAwsLibraryAccessors getAws() {
            return laccForEdcDataPlaneAwsLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.data.plane.azure</b>
         */
        public EdcDataPlaneAzureLibraryAccessors getAzure() {
            return laccForEdcDataPlaneAzureLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.data.plane.public</b>
         */
        public EdcDataPlanePublicLibraryAccessors getPublic() {
            return laccForEdcDataPlanePublicLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.data.plane.selector</b>
         */
        public EdcDataPlaneSelectorLibraryAccessors getSelector() {
            return laccForEdcDataPlaneSelectorLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.data.plane.self</b>
         */
        public EdcDataPlaneSelfLibraryAccessors getSelf() {
            return laccForEdcDataPlaneSelfLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.data.plane.signaling</b>
         */
        public EdcDataPlaneSignalingLibraryAccessors getSignaling() {
            return laccForEdcDataPlaneSignalingLibraryAccessors;
        }

    }

    public static class EdcDataPlaneAwsLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlaneAwsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>s3</b> with <b>org.eclipse.edc.aws:data-plane-aws-s3</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getS3() {
            return create("edc.data.plane.aws.s3");
        }

    }

    public static class EdcDataPlaneAzureLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlaneAzureLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>storage</b> with <b>org.eclipse.edc.azure:data-plane-azure-storage</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getStorage() {
            return create("edc.data.plane.azure.storage");
        }

    }

    public static class EdcDataPlanePublicLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlanePublicLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.eclipse.edc:data-plane-public-api-v2</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("edc.data.plane.public.api");
        }

    }

    public static class EdcDataPlaneSelectorLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlaneSelectorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.eclipse.edc:data-plane-selector-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("edc.data.plane.selector.api");
        }

        /**
         * Dependency provider for <b>core</b> with <b>org.eclipse.edc:data-plane-selector-core</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("edc.data.plane.selector.core");
        }

    }

    public static class EdcDataPlaneSelfLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlaneSelfLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>registration</b> with <b>org.eclipse.edc:data-plane-self-registration</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRegistration() {
            return create("edc.data.plane.self.registration");
        }

    }

    public static class EdcDataPlaneSignalingLibraryAccessors extends SubDependencyFactory {

        public EdcDataPlaneSignalingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.eclipse.edc:data-plane-signaling-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("edc.data.plane.signaling.api");
        }

        /**
         * Dependency provider for <b>client</b> with <b>org.eclipse.edc:data-plane-signaling-client</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getClient() {
            return create("edc.data.plane.signaling.client");
        }

    }

    public static class EdcEdrLibraryAccessors extends SubDependencyFactory {
        private final EdcEdrCacheLibraryAccessors laccForEdcEdrCacheLibraryAccessors = new EdcEdrCacheLibraryAccessors(owner);
        private final EdcEdrStoreLibraryAccessors laccForEdcEdrStoreLibraryAccessors = new EdcEdrStoreLibraryAccessors(owner);

        public EdcEdrLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.edr.cache</b>
         */
        public EdcEdrCacheLibraryAccessors getCache() {
            return laccForEdcEdrCacheLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.edr.store</b>
         */
        public EdcEdrStoreLibraryAccessors getStore() {
            return laccForEdcEdrStoreLibraryAccessors;
        }

    }

    public static class EdcEdrCacheLibraryAccessors extends SubDependencyFactory {

        public EdcEdrCacheLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.eclipse.edc:edr-cache-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("edc.edr.cache.api");
        }

    }

    public static class EdcEdrStoreLibraryAccessors extends SubDependencyFactory {

        public EdcEdrStoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.eclipse.edc:edr-store-core</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("edc.edr.store.core");
        }

        /**
         * Dependency provider for <b>receiver</b> with <b>org.eclipse.edc:edr-store-receiver</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getReceiver() {
            return create("edc.edr.store.receiver");
        }

    }

    public static class EdcFcLibraryAccessors extends SubDependencyFactory {
        private final EdcFcExtLibraryAccessors laccForEdcFcExtLibraryAccessors = new EdcFcExtLibraryAccessors(owner);
        private final EdcFcSpiLibraryAccessors laccForEdcFcSpiLibraryAccessors = new EdcFcSpiLibraryAccessors(owner);

        public EdcFcLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.eclipse.edc:federated-catalog-core</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("edc.fc.core");
        }

        /**
         * Group of libraries at <b>edc.fc.ext</b>
         */
        public EdcFcExtLibraryAccessors getExt() {
            return laccForEdcFcExtLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.fc.spi</b>
         */
        public EdcFcSpiLibraryAccessors getSpi() {
            return laccForEdcFcSpiLibraryAccessors;
        }

    }

    public static class EdcFcExtLibraryAccessors extends SubDependencyFactory {

        public EdcFcExtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.eclipse.edc:federated-catalog-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getApi() {
            return create("edc.fc.ext.api");
        }

    }

    public static class EdcFcSpiLibraryAccessors extends SubDependencyFactory {

        public EdcFcSpiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.eclipse.edc:federated-catalog-spi</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("edc.fc.spi.core");
        }

        /**
         * Dependency provider for <b>crawler</b> with <b>org.eclipse.edc:crawler-spi</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCrawler() {
            return create("edc.fc.spi.crawler");
        }

    }

    public static class EdcIamLibraryAccessors extends SubDependencyFactory {

        public EdcIamLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>mock</b> with <b>org.eclipse.edc:iam-mock</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMock() {
            return create("edc.iam.mock");
        }

    }

    public static class EdcJsonLibraryAccessors extends SubDependencyFactory {
        private final EdcJsonLdLibraryAccessors laccForEdcJsonLdLibraryAccessors = new EdcJsonLdLibraryAccessors(owner);

        public EdcJsonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.json.ld</b>
         */
        public EdcJsonLdLibraryAccessors getLd() {
            return laccForEdcJsonLdLibraryAccessors;
        }

    }

    public static class EdcJsonLdLibraryAccessors extends SubDependencyFactory {

        public EdcJsonLdLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>lib</b> with <b>org.eclipse.edc:json-ld-lib</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLib() {
            return create("edc.json.ld.lib");
        }

        /**
         * Dependency provider for <b>spi</b> with <b>org.eclipse.edc:json-ld-spi</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSpi() {
            return create("edc.json.ld.spi");
        }

    }

    public static class EdcManagementLibraryAccessors extends SubDependencyFactory {
        private final EdcManagementApiLibraryAccessors laccForEdcManagementApiLibraryAccessors = new EdcManagementApiLibraryAccessors(owner);

        public EdcManagementLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.management.api</b>
         */
        public EdcManagementApiLibraryAccessors getApi() {
            return laccForEdcManagementApiLibraryAccessors;
        }

    }

    public static class EdcManagementApiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final EdcManagementApiContractLibraryAccessors laccForEdcManagementApiContractLibraryAccessors = new EdcManagementApiContractLibraryAccessors(owner);
        private final EdcManagementApiTestLibraryAccessors laccForEdcManagementApiTestLibraryAccessors = new EdcManagementApiTestLibraryAccessors(owner);

        public EdcManagementApiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>api</b> with <b>org.eclipse.edc:management-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("edc.management.api");
        }

        /**
         * Dependency provider for <b>asset</b> with <b>org.eclipse.edc:asset-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAsset() {
            return create("edc.management.api.asset");
        }

        /**
         * Dependency provider for <b>catalog</b> with <b>org.eclipse.edc:catalog-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCatalog() {
            return create("edc.management.api.catalog");
        }

        /**
         * Group of libraries at <b>edc.management.api.contract</b>
         */
        public EdcManagementApiContractLibraryAccessors getContract() {
            return laccForEdcManagementApiContractLibraryAccessors;
        }

        /**
         * Group of libraries at <b>edc.management.api.test</b>
         */
        public EdcManagementApiTestLibraryAccessors getTest() {
            return laccForEdcManagementApiTestLibraryAccessors;
        }

    }

    public static class EdcManagementApiContractLibraryAccessors extends SubDependencyFactory {

        public EdcManagementApiContractLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>agreement</b> with <b>org.eclipse.edc:contract-agreement-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAgreement() {
            return create("edc.management.api.contract.agreement");
        }

        /**
         * Dependency provider for <b>definition</b> with <b>org.eclipse.edc:contract-definition-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getDefinition() {
            return create("edc.management.api.contract.definition");
        }

        /**
         * Dependency provider for <b>negotiation</b> with <b>org.eclipse.edc:contract-negotiation-api</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getNegotiation() {
            return create("edc.management.api.contract.negotiation");
        }

    }

    public static class EdcManagementApiTestLibraryAccessors extends SubDependencyFactory {

        public EdcManagementApiTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>fixtures</b> with <b>org.eclipse.edc:management-api-test-fixtures</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getFixtures() {
            return create("edc.management.api.test.fixtures");
        }

    }

    public static class EdcMonitorLibraryAccessors extends SubDependencyFactory {
        private final EdcMonitorJdkLibraryAccessors laccForEdcMonitorJdkLibraryAccessors = new EdcMonitorJdkLibraryAccessors(owner);

        public EdcMonitorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.monitor.jdk</b>
         */
        public EdcMonitorJdkLibraryAccessors getJdk() {
            return laccForEdcMonitorJdkLibraryAccessors;
        }

    }

    public static class EdcMonitorJdkLibraryAccessors extends SubDependencyFactory {

        public EdcMonitorJdkLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>logger</b> with <b>org.eclipse.edc:monitor-jdk-logger</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getLogger() {
            return create("edc.monitor.jdk.logger");
        }

    }

    public static class EdcTokenLibraryAccessors extends SubDependencyFactory {

        public EdcTokenLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>core</b> with <b>org.eclipse.edc:token-core</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getCore() {
            return create("edc.token.core");
        }

    }

    public static class EdcTransferLibraryAccessors extends SubDependencyFactory {
        private final EdcTransferDataLibraryAccessors laccForEdcTransferDataLibraryAccessors = new EdcTransferDataLibraryAccessors(owner);

        public EdcTransferLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.transfer.data</b>
         */
        public EdcTransferDataLibraryAccessors getData() {
            return laccForEdcTransferDataLibraryAccessors;
        }

    }

    public static class EdcTransferDataLibraryAccessors extends SubDependencyFactory {
        private final EdcTransferDataPlaneLibraryAccessors laccForEdcTransferDataPlaneLibraryAccessors = new EdcTransferDataPlaneLibraryAccessors(owner);

        public EdcTransferDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.transfer.data.plane</b>
         */
        public EdcTransferDataPlaneLibraryAccessors getPlane() {
            return laccForEdcTransferDataPlaneLibraryAccessors;
        }

    }

    public static class EdcTransferDataPlaneLibraryAccessors extends SubDependencyFactory {

        public EdcTransferDataPlaneLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>signaling</b> with <b>org.eclipse.edc:transfer-data-plane-signaling</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSignaling() {
            return create("edc.transfer.data.plane.signaling");
        }

    }

    public static class EdcValidatorLibraryAccessors extends SubDependencyFactory {
        private final EdcValidatorDataLibraryAccessors laccForEdcValidatorDataLibraryAccessors = new EdcValidatorDataLibraryAccessors(owner);

        public EdcValidatorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.validator.data</b>
         */
        public EdcValidatorDataLibraryAccessors getData() {
            return laccForEdcValidatorDataLibraryAccessors;
        }

    }

    public static class EdcValidatorDataLibraryAccessors extends SubDependencyFactory {
        private final EdcValidatorDataAddressLibraryAccessors laccForEdcValidatorDataAddressLibraryAccessors = new EdcValidatorDataAddressLibraryAccessors(owner);

        public EdcValidatorDataLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.validator.data.address</b>
         */
        public EdcValidatorDataAddressLibraryAccessors getAddress() {
            return laccForEdcValidatorDataAddressLibraryAccessors;
        }

    }

    public static class EdcValidatorDataAddressLibraryAccessors extends SubDependencyFactory {
        private final EdcValidatorDataAddressHttpLibraryAccessors laccForEdcValidatorDataAddressHttpLibraryAccessors = new EdcValidatorDataAddressHttpLibraryAccessors(owner);

        public EdcValidatorDataAddressLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Group of libraries at <b>edc.validator.data.address.http</b>
         */
        public EdcValidatorDataAddressHttpLibraryAccessors getHttp() {
            return laccForEdcValidatorDataAddressHttpLibraryAccessors;
        }

    }

    public static class EdcValidatorDataAddressHttpLibraryAccessors extends SubDependencyFactory {

        public EdcValidatorDataAddressHttpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>data</b> with <b>org.eclipse.edc:validator-data-address-http-data</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getData() {
            return create("edc.validator.data.address.http.data");
        }

    }

    public static class EdcVaultLibraryAccessors extends SubDependencyFactory {

        public EdcVaultLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>hashicorp</b> with <b>org.eclipse.edc:vault-hashicorp</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getHashicorp() {
            return create("edc.vault.hashicorp");
        }

    }

    public static class EdcWebLibraryAccessors extends SubDependencyFactory {

        public EdcWebLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>spi</b> with <b>org.eclipse.edc:web-spi</b> coordinates and
         * with version reference <b>edc</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getSpi() {
            return create("edc.web.spi");
        }

    }

    public static class JakartaLibraryAccessors extends SubDependencyFactory {

        public JakartaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>rsApi</b> with <b>jakarta.ws.rs:jakarta.ws.rs-api</b> coordinates and
         * with version reference <b>rsApi</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRsApi() {
            return create("jakarta.rsApi");
        }

    }

    public static class KafkaLibraryAccessors extends SubDependencyFactory {

        public KafkaLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>clients</b> with <b>org.apache.kafka:kafka-clients</b> coordinates and
         * with version reference <b>kafkaClients</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getClients() {
            return create("kafka.clients");
        }

    }

    public static class MinioLibraryAccessors extends SubDependencyFactory {

        public MinioLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>io</b> with <b>io.minio:minio</b> coordinates and
         * with version <b>8.5.17</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getIo() {
            return create("minio.io");
        }

    }

    public static class OkhttpLibraryAccessors extends SubDependencyFactory {

        public OkhttpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>mockwebserver</b> with <b>com.squareup.okhttp3:mockwebserver</b> coordinates and
         * with version reference <b>okhttp.mockwebserver</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMockwebserver() {
            return create("okhttp.mockwebserver");
        }

    }

    public static class OpentelemetryLibraryAccessors extends SubDependencyFactory {
        private final OpentelemetryExporterLibraryAccessors laccForOpentelemetryExporterLibraryAccessors = new OpentelemetryExporterLibraryAccessors(owner);

        public OpentelemetryLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>annotations</b> with <b>io.opentelemetry:opentelemetry-extension-annotations</b> coordinates and
         * with version <b>1.18.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getAnnotations() {
            return create("opentelemetry.annotations");
        }

        /**
         * Dependency provider for <b>javaagent</b> with <b>io.opentelemetry.javaagent:opentelemetry-javaagent</b> coordinates and
         * with version <b>2.16.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJavaagent() {
            return create("opentelemetry.javaagent");
        }

        /**
         * Group of libraries at <b>opentelemetry.exporter</b>
         */
        public OpentelemetryExporterLibraryAccessors getExporter() {
            return laccForOpentelemetryExporterLibraryAccessors;
        }

    }

    public static class OpentelemetryExporterLibraryAccessors extends SubDependencyFactory {

        public OpentelemetryExporterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>otlp</b> with <b>io.opentelemetry:opentelemetry-exporter-otlp</b> coordinates and
         * with version <b>1.50.0</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getOtlp() {
            return create("opentelemetry.exporter.otlp");
        }

    }

    public static class TestcontainersLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final TestcontainersHashicorpLibraryAccessors laccForTestcontainersHashicorpLibraryAccessors = new TestcontainersHashicorpLibraryAccessors(owner);

        public TestcontainersLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>testcontainers</b> with <b>org.testcontainers:testcontainers</b> coordinates and
         * with version reference <b>testcontainers</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> asProvider() {
            return create("testcontainers");
        }

        /**
         * Dependency provider for <b>junit</b> with <b>org.testcontainers:junit-jupiter</b> coordinates and
         * with version reference <b>testcontainers</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getJunit() {
            return create("testcontainers.junit");
        }

        /**
         * Dependency provider for <b>kafka</b> with <b>org.testcontainers:kafka</b> coordinates and
         * with version reference <b>testcontainers</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getKafka() {
            return create("testcontainers.kafka");
        }

        /**
         * Dependency provider for <b>minio</b> with <b>org.testcontainers:minio</b> coordinates and
         * with version reference <b>testcontainers</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getMinio() {
            return create("testcontainers.minio");
        }

        /**
         * Group of libraries at <b>testcontainers.hashicorp</b>
         */
        public TestcontainersHashicorpLibraryAccessors getHashicorp() {
            return laccForTestcontainersHashicorpLibraryAccessors;
        }

    }

    public static class TestcontainersHashicorpLibraryAccessors extends SubDependencyFactory {

        public TestcontainersHashicorpLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Dependency provider for <b>vault</b> with <b>org.testcontainers:vault</b> coordinates and
         * with version reference <b>testcontainers</b>
         * <p>
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getVault() {
            return create("testcontainers.hashicorp.vault");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final OkhttpVersionAccessors vaccForOkhttpVersionAccessors = new OkhttpVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>awaitility</b> with value <b>4.2.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getAwaitility() { return getVersion("awaitility"); }

        /**
         * Version alias <b>edc</b> with value <b>0.12.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getEdc() { return getVersion("edc"); }

        /**
         * Version alias <b>kafkaClients</b> with value <b>4.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getKafkaClients() { return getVersion("kafkaClients"); }

        /**
         * Version alias <b>restAssured</b> with value <b>5.5.2</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRestAssured() { return getVersion("restAssured"); }

        /**
         * Version alias <b>rsApi</b> with value <b>4.0.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getRsApi() { return getVersion("rsApi"); }

        /**
         * Version alias <b>testcontainers</b> with value <b>1.21.0</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getTestcontainers() { return getVersion("testcontainers"); }

        /**
         * Group of versions at <b>versions.okhttp</b>
         */
        public OkhttpVersionAccessors getOkhttp() {
            return vaccForOkhttpVersionAccessors;
        }

    }

    public static class OkhttpVersionAccessors extends VersionFactory  {

        public OkhttpVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Version alias <b>okhttp.mockwebserver</b> with value <b>5.0.0-alpha.14</b>
         * <p>
         * If the version is a rich version and cannot be represented as a
         * single version string, an empty string is returned.
         * <p>
         * This version was declared in catalog libs.versions.toml
         */
        public Provider<String> getMockwebserver() { return getVersion("okhttp.mockwebserver"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Plugin provider for <b>shadow</b> with plugin id <b>com.gradleup.shadow</b> and
         * with version <b>8.3.6</b>
         * <p>
         * This plugin was declared in catalog libs.versions.toml
         */
        public Provider<PluginDependency> getShadow() { return createPlugin("shadow"); }

    }

}

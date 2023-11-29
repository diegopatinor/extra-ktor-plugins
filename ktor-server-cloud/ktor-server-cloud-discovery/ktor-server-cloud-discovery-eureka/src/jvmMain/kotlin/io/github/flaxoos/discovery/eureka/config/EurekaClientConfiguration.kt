package io.github.flaxoos.discovery.eureka.config

import com.netflix.discovery.DefaultEurekaClientConfig
import com.netflix.discovery.EurekaClientConfig
import com.netflix.discovery.shared.transport.EurekaTransportConfig
import io.ktor.server.config.ApplicationConfig

public class EurekaClientConfiguration internal constructor(
    @get:JvmName("_namespace")
    public var namespace: String,

    @get:JvmName("_registryFetchIntervalSeconds")
    public var registryFetchIntervalSeconds: Int? = null,

    @get:JvmName("_instanceInfoReplicationIntervalSeconds")
    public var instanceInfoReplicationIntervalSeconds: Int? = null,

    @get:JvmName("_initialInstanceInfoReplicationIntervalSeconds")
    public var initialInstanceInfoReplicationIntervalSeconds: Int? = null,

    @get:JvmName("_eurekaServiceUrlPollIntervalSeconds")
    public var eurekaServiceUrlPollIntervalSeconds: Int? = null,

    @get:JvmName("_proxyHost")
    public var proxyHost: String? = null,

    @get:JvmName("_proxyPort")
    public var proxyPort: String? = null,

    @get:JvmName("_proxyUserName")
    public var proxyUserName: String? = null,

    @get:JvmName("_proxyPassword")
    public var proxyPassword: String? = null,

    @get:JvmName("_shouldGZipContent")
    public var shouldGZipContent: Boolean? = null,

    @get:JvmName("_eurekaServerReadTimeoutSeconds")
    public var eurekaServerReadTimeoutSeconds: Int? = null,

    @get:JvmName("_eurekaServerConnectTimeoutSeconds")
    public var eurekaServerConnectTimeoutSeconds: Int? = null,

    @get:JvmName("_backupRegistryImpl")
    public var backupRegistryImpl: String? = null,

    @get:JvmName("_eurekaServerTotalConnections")
    public var eurekaServerTotalConnections: Int? = null,

    @get:JvmName("_eurekaServerTotalConnectionsPerHost")
    public var eurekaServerTotalConnectionsPerHost: Int? = null,

    @get:JvmName("_eurekaServerURLContext")
    public var eurekaServerURLContext: String? = null,

    @get:JvmName("_eurekaServerPort")
    public var eurekaServerPort: String? = null,

    @get:JvmName("_eurekaServerDNSName")
    public var eurekaServerDNSName: String? = null,

    @get:JvmName("_shouldUseDnsForFetchingServiceUrls")
    public var shouldUseDnsForFetchingServiceUrls: Boolean? = null,

    @get:JvmName("_shouldRegisterWithEureka")
    public var shouldRegisterWithEureka: Boolean? = null,

    @get:JvmName("_shouldPreferSameZoneEureka")
    public var shouldPreferSameZoneEureka: Boolean? = null,

    @get:JvmName("_allowRedirects")
    public var allowRedirects: Boolean? = null,

    @get:JvmName("_shouldLogDeltaDiff")
    public var shouldLogDeltaDiff: Boolean? = null,

    @get:JvmName("_shouldDisableDelta")
    public var shouldDisableDelta: Boolean? = null,

    @get:JvmName("_fetchRegistryForRemoteRegions")
    public var fetchRegistryForRemoteRegions: String? = null,

    @get:JvmName("_region")
    public var region: String? = null,

    @get:JvmName("_availabilityZones")
    public var availabilityZones: Map<String, List<String>>? = null,

    @get:JvmName("_eurekaServerServiceUrls")
    public var eurekaServerServiceUrls: Map<String, List<String>>? = null,

    @get:JvmName("_shouldFilterOnlyUpInstances")
    public var shouldFilterOnlyUpInstances: Boolean? = null,

    @get:JvmName("_eurekaConnectionIdleTimeoutSeconds")
    public var eurekaConnectionIdleTimeoutSeconds: Int? = null,

    @get:JvmName("_shouldFetchRegistry")
    public var shouldFetchRegistry: Boolean? = null,

    @get:JvmName("_registryRefreshSingleVipAddress")
    public var registryRefreshSingleVipAddress: String? = null,

    @get:JvmName("_heartbeatExecutorThreadPoolSize")
    public var heartbeatExecutorThreadPoolSize: Int? = null,

    @get:JvmName("_heartbeatExecutorExponentialBackOffBound")
    public var heartbeatExecutorExponentialBackOffBound: Int? = null,

    @get:JvmName("_cacheRefreshExecutorThreadPoolSize")
    public var cacheRefreshExecutorThreadPoolSize: Int? = null,

    @get:JvmName("_cacheRefreshExecutorExponentialBackOffBound")
    public var cacheRefreshExecutorExponentialBackOffBound: Int? = null,

    @get:JvmName("_dollarReplacement")
    public var dollarReplacement: String? = null,

    @get:JvmName("_escapeCharReplacement")
    public var escapeCharReplacement: String? = null,

    @get:JvmName("_shouldOnDemandUpdateStatusChange")
    public var shouldOnDemandUpdateStatusChange: Boolean? = null,

    @get:JvmName("_encoderName")
    public var encoderName: String? = null,

    @get:JvmName("_decoderName")
    public var decoderName: String? = null,

    @get:JvmName("_clientDataAccept")
    public var clientDataAccept: String? = null,

    @get:JvmName("_experimental")
    public var experimental: String? = null,

    @get:JvmName("_transportConfig")
    public var transportConfig: EurekaTransportConfig? = null
) : EurekaClientConfig {
    private val delegate = DefaultEurekaClientConfig(namespace)
    override fun getRegistryFetchIntervalSeconds(): Int =
        registryFetchIntervalSeconds ?: delegate.registryFetchIntervalSeconds

    override fun getInstanceInfoReplicationIntervalSeconds(): Int =
        instanceInfoReplicationIntervalSeconds ?: delegate.instanceInfoReplicationIntervalSeconds

    override fun getInitialInstanceInfoReplicationIntervalSeconds(): Int =
        initialInstanceInfoReplicationIntervalSeconds ?: delegate.initialInstanceInfoReplicationIntervalSeconds

    override fun getEurekaServiceUrlPollIntervalSeconds(): Int =
        eurekaServiceUrlPollIntervalSeconds ?: delegate.eurekaServiceUrlPollIntervalSeconds

    override fun getProxyHost(): String? =
        proxyHost ?: delegate.proxyHost

    override fun getProxyPort(): String? =
        proxyPort ?: delegate.proxyPort

    override fun getProxyUserName(): String? =
        proxyUserName ?: delegate.proxyUserName

    override fun getProxyPassword(): String? =
        proxyPassword ?: delegate.proxyPassword

    @Deprecated("Deprecated in Java")
    override fun shouldGZipContent(): Boolean =
        shouldGZipContent ?: delegate.shouldGZipContent()

    override fun getEurekaServerReadTimeoutSeconds(): Int =
        eurekaServerReadTimeoutSeconds ?: delegate.eurekaServerReadTimeoutSeconds

    override fun getEurekaServerConnectTimeoutSeconds(): Int =
        eurekaServerConnectTimeoutSeconds ?: delegate.eurekaServerConnectTimeoutSeconds

    override fun getBackupRegistryImpl(): String? =
        backupRegistryImpl ?: delegate.backupRegistryImpl

    override fun getEurekaServerTotalConnections(): Int =
        eurekaServerTotalConnections ?: delegate.eurekaServerTotalConnections

    override fun getEurekaServerTotalConnectionsPerHost(): Int =
        eurekaServerTotalConnectionsPerHost ?: delegate.eurekaServerTotalConnectionsPerHost

    override fun getEurekaServerURLContext(): String? =
        eurekaServerURLContext ?: delegate.eurekaServerURLContext

    override fun getEurekaServerPort(): String? =
        eurekaServerPort ?: delegate.eurekaServerPort

    override fun getEurekaServerDNSName(): String? =
        eurekaServerDNSName ?: delegate.eurekaServerDNSName

    override fun shouldUseDnsForFetchingServiceUrls(): Boolean =
        shouldUseDnsForFetchingServiceUrls ?: delegate.shouldUseDnsForFetchingServiceUrls()

    override fun shouldRegisterWithEureka(): Boolean =
        shouldRegisterWithEureka ?: delegate.shouldRegisterWithEureka()

    override fun shouldPreferSameZoneEureka(): Boolean =
        shouldPreferSameZoneEureka ?: delegate.shouldPreferSameZoneEureka()

    override fun allowRedirects(): Boolean =
        allowRedirects ?: delegate.allowRedirects()

    override fun shouldLogDeltaDiff(): Boolean =
        shouldLogDeltaDiff ?: delegate.shouldLogDeltaDiff()

    override fun shouldDisableDelta(): Boolean =
        shouldDisableDelta ?: delegate.shouldDisableDelta()

    override fun fetchRegistryForRemoteRegions(): String? =
        fetchRegistryForRemoteRegions ?: delegate.fetchRegistryForRemoteRegions()

    override fun getRegion(): String =
        region ?: delegate.region

    override fun getAvailabilityZones(region: String?): Array<String> =
        availabilityZones?.get(region)?.toTypedArray() ?: delegate.getAvailabilityZones(region)

    override fun getEurekaServerServiceUrls(myZone: String?): MutableList<String>? =
        eurekaServerServiceUrls?.get(myZone)?.toMutableList() ?: delegate.getEurekaServerServiceUrls(myZone)

    override fun shouldFilterOnlyUpInstances(): Boolean =
        shouldFilterOnlyUpInstances ?: delegate.shouldFilterOnlyUpInstances()

    override fun getEurekaConnectionIdleTimeoutSeconds(): Int =
        eurekaConnectionIdleTimeoutSeconds ?: delegate.eurekaConnectionIdleTimeoutSeconds

    override fun shouldFetchRegistry(): Boolean =
        shouldFetchRegistry ?: delegate.shouldFetchRegistry()

    override fun getRegistryRefreshSingleVipAddress(): String? =
        registryRefreshSingleVipAddress ?: delegate.registryRefreshSingleVipAddress

    override fun getHeartbeatExecutorThreadPoolSize(): Int =
        heartbeatExecutorThreadPoolSize ?: delegate.heartbeatExecutorThreadPoolSize

    override fun getHeartbeatExecutorExponentialBackOffBound(): Int =
        heartbeatExecutorExponentialBackOffBound ?: delegate.heartbeatExecutorExponentialBackOffBound

    override fun getCacheRefreshExecutorThreadPoolSize(): Int =
        cacheRefreshExecutorThreadPoolSize ?: delegate.cacheRefreshExecutorThreadPoolSize

    override fun getCacheRefreshExecutorExponentialBackOffBound(): Int =
        cacheRefreshExecutorExponentialBackOffBound ?: delegate.cacheRefreshExecutorExponentialBackOffBound

    override fun getDollarReplacement(): String =
        dollarReplacement ?: delegate.dollarReplacement

    override fun getEscapeCharReplacement(): String =
        escapeCharReplacement ?: delegate.escapeCharReplacement

    override fun shouldOnDemandUpdateStatusChange(): Boolean =
        shouldOnDemandUpdateStatusChange ?: delegate.shouldOnDemandUpdateStatusChange()

    override fun getEncoderName(): String? =
        encoderName ?: delegate.encoderName

    override fun getDecoderName(): String? =
        decoderName ?: delegate.decoderName

    override fun getClientDataAccept(): String =
        clientDataAccept ?: delegate.clientDataAccept

    override fun getExperimental(name: String?): String? =
        experimental ?: delegate.getExperimental(name)

    override fun getTransportConfig(): EurekaTransportConfig? =
        transportConfig ?: delegate.transportConfig

    internal companion object {
        internal fun ApplicationConfig.readEurekaClientConfiguration(namespace: String): EurekaClientConfiguration =
            with(config(namespace)) {
                EurekaClientConfiguration(
                    registryFetchIntervalSeconds = propertyOrNull("registryFetchIntervalSeconds")?.getString()
                        ?.toInt(),
                    instanceInfoReplicationIntervalSeconds = propertyOrNull("instanceInfoReplicationIntervalSeconds")?.getString()
                        ?.toInt(),
                    initialInstanceInfoReplicationIntervalSeconds = propertyOrNull("initialInstanceInfoReplicationIntervalSeconds")?.getString()
                        ?.toInt(),
                    eurekaServiceUrlPollIntervalSeconds = propertyOrNull("eurekaServiceUrlPollIntervalSeconds")?.getString()
                        ?.toInt(),
                    proxyHost = propertyOrNull("proxyHost")?.getString(),
                    proxyPort = propertyOrNull("proxyPort")?.getString(),
                    proxyUserName = propertyOrNull("proxyUserName")?.getString(),
                    proxyPassword = propertyOrNull("proxyPassword")?.getString(),
                    shouldGZipContent = propertyOrNull("shouldGZipContent")?.getString()?.toBoolean(),
                    eurekaServerReadTimeoutSeconds = propertyOrNull("eurekaServerReadTimeoutSeconds")?.getString()
                        ?.toInt(),
                    eurekaServerConnectTimeoutSeconds = propertyOrNull("eurekaServerConnectTimeoutSeconds")?.getString()
                        ?.toInt(),
                    backupRegistryImpl = propertyOrNull("backupRegistryImpl")?.getString(),
                    eurekaServerTotalConnections = propertyOrNull("eurekaServerTotalConnections")?.getString()
                        ?.toInt(),
                    eurekaServerTotalConnectionsPerHost = propertyOrNull("eurekaServerTotalConnectionsPerHost")?.getString()
                        ?.toInt(),
                    eurekaServerURLContext = propertyOrNull("eurekaServerURLContext")?.getString(),
                    eurekaServerPort = propertyOrNull("eurekaServerPort")?.getString(),
                    eurekaServerDNSName = propertyOrNull("eurekaServerDNSName")?.getString(),
                    shouldUseDnsForFetchingServiceUrls = propertyOrNull("shouldUseDnsForFetchingServiceUrls")?.getString()
                        ?.toBoolean(),
                    shouldRegisterWithEureka = propertyOrNull("shouldRegisterWithEureka")?.getString()?.toBoolean(),
                    shouldPreferSameZoneEureka = propertyOrNull("shouldPreferSameZoneEureka")?.getString()
                        ?.toBoolean(),
                    allowRedirects = propertyOrNull("allowRedirects")?.getString()?.toBoolean(),
                    shouldLogDeltaDiff = propertyOrNull("shouldLogDeltaDiff")?.getString()?.toBoolean(),
                    shouldDisableDelta = propertyOrNull("shouldDisableDelta")?.getString()?.toBoolean(),
                    fetchRegistryForRemoteRegions = propertyOrNull("fetchRegistryForRemoteRegions")?.getString(),
                    region = propertyOrNull("region")?.getString(),
                    eurekaServerServiceUrls = configOrNull("eurekaServerServiceUrls")?.toMap()
                        ?.mapValues { it.toString().split(",") },
                    availabilityZones = configOrNull("availabilityZones")?.toMap()
                        ?.mapValues { it.toString().split(",") },
                    shouldFilterOnlyUpInstances = propertyOrNull("shouldFilterOnlyUpInstances")?.getString()
                        ?.toBoolean(),
                    eurekaConnectionIdleTimeoutSeconds = propertyOrNull("eurekaConnectionIdleTimeoutSeconds")?.getString()
                        ?.toInt(),
                    shouldFetchRegistry = propertyOrNull("shouldFetchRegistry")?.getString()?.toBoolean(),
                    registryRefreshSingleVipAddress = propertyOrNull("registryRefreshSingleVipAddress")?.getString(),
                    heartbeatExecutorThreadPoolSize = propertyOrNull("heartbeatExecutorThreadPoolSize")?.getString()
                        ?.toInt(),
                    heartbeatExecutorExponentialBackOffBound = propertyOrNull("heartbeatExecutorExponentialBackOffBound")?.getString()
                        ?.toInt(),
                    cacheRefreshExecutorThreadPoolSize = propertyOrNull("cacheRefreshExecutorThreadPoolSize")?.getString()
                        ?.toInt(),
                    cacheRefreshExecutorExponentialBackOffBound = propertyOrNull("cacheRefreshExecutorExponentialBackOffBound")?.getString()
                        ?.toInt(),
                    dollarReplacement = propertyOrNull("dollarReplacement")?.getString(),
                    escapeCharReplacement = propertyOrNull("escapeCharReplacement")?.getString(),
                    shouldOnDemandUpdateStatusChange = propertyOrNull("shouldOnDemandUpdateStatusChange")?.getString()
                        ?.toBoolean(),
                    encoderName = propertyOrNull("encoderName")?.getString(),
                    decoderName = propertyOrNull("decoderName")?.getString(),
                    clientDataAccept = propertyOrNull("clientDataAccept")?.getString(),
                    experimental = propertyOrNull("experimental")?.getString(),
                    namespace = namespace
                )
            }
    }
}

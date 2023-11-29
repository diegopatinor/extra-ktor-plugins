package io.github.flaxoos.discovery.eureka.config

import com.netflix.appinfo.CloudInstanceConfig
import com.netflix.appinfo.DataCenterInfo
import com.netflix.appinfo.EurekaInstanceConfig
import com.netflix.appinfo.MyDataCenterInstanceConfig
import io.ktor.server.config.ApplicationConfig
import io.ktor.server.config.ApplicationConfigurationException

public class EurekaInstanceConfiguration internal constructor(
    @get:JvmName("_isAws")
    public var isAws: Boolean = false,

    @get:JvmName("_namespace")
    public var namespace: String,

    @get:JvmName("_instanceId")
    public var instanceId: String? = null,

    @get:JvmName("_appname")
    public var appname: String? = null,

    @get:JvmName("_appGroupName")
    public var appGroupName: String? = null,

    @get:JvmName("_isInstanceEnabledOnit")
    public var isInstanceEnabledOnit: Boolean? = null,

    @get:JvmName("_nonSecurePort")
    public var nonSecurePort: Int? = null,

    @get:JvmName("_securePort")
    public var securePort: Int? = null,

    @get:JvmName("_isNonSecurePortEnabled")
    public var isNonSecurePortEnabled: Boolean? = null,

    @get:JvmName("_securePortEnabled")
    public var securePortEnabled: Boolean? = null,

    @get:JvmName("_leaseRenewalIntervalInSeconds")
    public var leaseRenewalIntervalInSeconds: Int? = null,

    @get:JvmName("_leaseExpirationDurationInSeconds")
    public var leaseExpirationDurationInSeconds: Int? = null,

    @get:JvmName("_virtualHostName")
    public var virtualHostName: String? = null,

    @get:JvmName("_secureVirtualHostName")
    public var secureVirtualHostName: String? = null,

    @get:JvmName("_asgName")
    public var asgName: String? = null,

    @get:JvmName("_metadataMap")
    public var metadataMap: Map<String, String>? = null,

    @get:JvmName("_ipAddress")
    public var ipAddress: String? = null,

    @get:JvmName("_statusPageUrlPath")
    public var statusPageUrlPath: String? = null,

    @get:JvmName("_statusPageUrl")
    public var statusPageUrl: String? = null,

    @get:JvmName("_homePageUrlPath")
    public var homePageUrlPath: String? = null,

    @get:JvmName("_homePageUrl")
    public var homePageUrl: String? = null,

    @get:JvmName("_healthCheckUrlPath")
    public var healthCheckUrlPath: String? = null,

    @get:JvmName("_healthCheckUrl")
    public var healthCheckUrl: String? = null,

    @get:JvmName("_secureHealthCheckUrl")
    public var secureHealthCheckUrl: String? = null,

    @get:JvmName("_defaultAddressResolutionOrder")
    public var defaultAddressResolutionOrder: List<String>? = null
)  : EurekaInstanceConfig {
    private val delegate: EurekaInstanceConfig =
        if (isAws) CloudInstanceConfig(namespace) else MyDataCenterInstanceConfig(namespace)

    override fun getInstanceId(): String? = instanceId ?: delegate.instanceId
    override fun getAppname(): String? = appname ?: delegate.appname
    override fun getAppGroupName(): String? = appGroupName ?: delegate.appGroupName
    override fun isInstanceEnabledOnit(): Boolean = isInstanceEnabledOnit ?: delegate.isInstanceEnabledOnit
    override fun getNonSecurePort(): Int = nonSecurePort ?: delegate.nonSecurePort
    override fun getSecurePort(): Int = securePort ?: delegate.securePort
    override fun isNonSecurePortEnabled(): Boolean = isNonSecurePortEnabled ?: delegate.isNonSecurePortEnabled
    override fun getSecurePortEnabled(): Boolean = securePortEnabled ?: delegate.securePortEnabled
    override fun getLeaseRenewalIntervalInSeconds(): Int =
        leaseRenewalIntervalInSeconds ?: delegate.leaseRenewalIntervalInSeconds

    override fun getLeaseExpirationDurationInSeconds(): Int =
        leaseExpirationDurationInSeconds ?: delegate.leaseExpirationDurationInSeconds

    override fun getVirtualHostName(): String? = virtualHostName ?: delegate.virtualHostName
    override fun getSecureVirtualHostName(): String? = secureVirtualHostName ?: delegate.secureVirtualHostName
    override fun getASGName(): String? = asgName ?: delegate.asgName
    override fun getHostName(refresh: Boolean): String = delegate.getHostName(refresh)
    override fun getMetadataMap(): Map<String, String>? = metadataMap?.toMap() ?: delegate.metadataMap
    override fun getDataCenterInfo(): DataCenterInfo? = delegate.dataCenterInfo
    override fun getIpAddress(): String? = ipAddress ?: delegate.ipAddress
    override fun getStatusPageUrlPath(): String? = statusPageUrlPath ?: delegate.statusPageUrlPath
    override fun getStatusPageUrl(): String? = statusPageUrl ?: delegate.statusPageUrl
    override fun getHomePageUrlPath(): String? = homePageUrlPath ?: delegate.homePageUrlPath
    override fun getHomePageUrl(): String? = homePageUrl ?: delegate.homePageUrl
    override fun getHealthCheckUrlPath(): String? = healthCheckUrlPath ?: delegate.healthCheckUrlPath
    override fun getHealthCheckUrl(): String? = healthCheckUrl ?: delegate.healthCheckUrl
    override fun getSecureHealthCheckUrl(): String? = secureHealthCheckUrl ?: delegate.secureHealthCheckUrl
    override fun getDefaultAddressResolutionOrder(): Array<String>? =
        defaultAddressResolutionOrder?.toTypedArray() ?: delegate.defaultAddressResolutionOrder

    override fun getNamespace(): String = namespace

}

internal fun ApplicationConfig.readEurekaInstanceConfiguration(namespace: String): EurekaInstanceConfiguration =
    with(config(namespace)) {
        EurekaInstanceConfiguration(
            instanceId = propertyOrNull("instanceId")?.getString(),
            appname = propertyOrNull("appname")?.getString(),
            appGroupName = propertyOrNull("appGroupName")?.getString(),
            isInstanceEnabledOnit = propertyOrNull("instanceEnabledOnit")?.getString()?.toBoolean(),
            nonSecurePort = propertyOrNull("nonSecurePort")?.getString()?.toInt(),
            securePort = propertyOrNull("securePort")?.getString()?.toInt(),
            isNonSecurePortEnabled = propertyOrNull("nonSecurePortEnabled")?.getString()?.toBoolean(),
            securePortEnabled = propertyOrNull("securePortEnabled")?.getString()?.toBoolean(),
            leaseRenewalIntervalInSeconds = propertyOrNull("leaseRenewalIntervalInSeconds")?.getString()?.toInt(),
            leaseExpirationDurationInSeconds = propertyOrNull("leaseExpirationDurationInSeconds")?.getString()
                ?.toInt(),
            virtualHostName = propertyOrNull("virtualHostName")?.getString(),
            secureVirtualHostName = propertyOrNull("secureVirtualHostName")?.getString(),
            asgName = propertyOrNull("asgName")?.getString(),
            metadataMap = runCatching { config("metadataMap") }.onFailure { if (it !is ApplicationConfigurationException) throw it }
                .getOrNull()?.toMap()?.mapValues { it.toString() },
            ipAddress = propertyOrNull("ipAddress")?.getString(),
            statusPageUrlPath = propertyOrNull("statusPageUrlPath")?.getString(),
            statusPageUrl = propertyOrNull("statusPageUrl")?.getString(),
            homePageUrlPath = propertyOrNull("homePageUrlPath")?.getString(),
            homePageUrl = propertyOrNull("homePageUrl")?.getString(),
            healthCheckUrlPath = propertyOrNull("healthCheckUrlPath")?.getString(),
            healthCheckUrl = propertyOrNull("healthCheckUrl")?.getString(),
            secureHealthCheckUrl = propertyOrNull("secureHealthCheckUrl")?.getString(),
            defaultAddressResolutionOrder = propertyOrNull("defaultAddressResolutionOrder")?.getList(),
            namespace = namespace
        )
    }

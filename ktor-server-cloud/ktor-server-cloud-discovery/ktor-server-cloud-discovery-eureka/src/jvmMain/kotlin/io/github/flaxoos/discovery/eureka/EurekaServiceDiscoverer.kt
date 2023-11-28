package io.github.flaxoos.discovery.eureka

import com.netflix.appinfo.ApplicationInfoManager
import com.netflix.appinfo.EurekaInstanceConfig
import com.netflix.appinfo.InstanceInfo
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider
import com.netflix.discovery.DefaultEurekaClientConfig
import com.netflix.discovery.DiscoveryClient
import com.netflix.discovery.EurekaClientConfig
import com.netflix.discovery.shared.Application
import io.github.flaxoos.cloud.discovery.DiscoverableService
import io.github.flaxoos.cloud.discovery.DiscoverableServiceId
import io.github.flaxoos.cloud.discovery.DiscoverableServiceId.Companion.asDiscoverableServiceId
import io.github.flaxoos.cloud.discovery.DiscoverableServiceInstanceId.Companion.asDiscoverableServiceInstanceId
import io.github.flaxoos.cloud.discovery.DiscoverableServiceName.Companion.asDiscoverableServiceName
import io.github.flaxoos.cloud.discovery.ServiceDiscoverer
import io.github.flaxoos.discovery.eureka.EurekaClientConfiguration.Companion.readEurekaClientConfiguration
import io.ktor.http.Url

internal class EurekaServiceDiscoverer(
    application: io.ktor.server.application.Application,
    eurekaClientConfiguration: EurekaClientConfiguration? = null,
    eurekaInstanceConfiguration: EurekaInstanceConfiguration? = null,
    namespace: String
) : ServiceDiscoverer {

    private val clientConfig: EurekaClientConfig =
        eurekaClientConfiguration ?: application.environment.config.readEurekaClientConfiguration()
    private val instanceConfig: EurekaInstanceConfig =
        eurekaInstanceConfiguration ?: application.environment.config.readEurekaInstanceConfiguration(namespace)
    private val instanceInfo: InstanceInfo = EurekaConfigBasedInstanceInfoProvider(instanceConfig).get()
    val applicationInfoManager: ApplicationInfoManager = ApplicationInfoManager(instanceConfig, instanceInfo)
    val eurekaClient = DiscoveryClient(
        applicationInfoManager,
        clientConfig
    )

    override fun getServices(): List<DiscoverableService> {
        return eurekaClient.applications.registeredApplications.map {
            it.toEurekaDiscoverableService()
        }
    }

    override fun getServiceInstances(serviceId: DiscoverableServiceId): List<EurekaDiscoverableServiceInstance> {
        return eurekaClient.getApplication(serviceId.value.asDiscoverableServiceName().value).instances.map {
            it.toEurekaDiscoverableServiceInstance()
        }
    }

    override fun getNextApplicationInstance(vipAddress: String, secure: Boolean): EurekaDiscoverableServiceInstance? {
        return eurekaClient.getNextServerFromEureka(vipAddress, secure)?.toEurekaDiscoverableServiceInstance()
    }

}

private fun InstanceInfo.toEurekaDiscoverableServiceInstance(): EurekaDiscoverableServiceInstance {
    return EurekaDiscoverableServiceInstance(
        id = id.asDiscoverableServiceInstanceId(),
        serviceId = this.appName.asDiscoverableServiceId(),
        hostName = hostName,
        ipAddress = ipAddr,
        vipAddress = vipAddress,
        port = if (secure) this.securePort else this.port,
        secure = this.secure,
        url = Url("${if (secure) "https" else "http"}://${hostName}:${port}")
    )
}

private fun Application.toEurekaDiscoverableService(): EurekaDiscoverableService {
    return EurekaDiscoverableService(
        id = this.name.asDiscoverableServiceId(),
        tags = emptyList(),
        meta = emptyMap(),
    )
}
package io.github.flaxoos.discovery.eureka

import com.netflix.appinfo.AbstractInstanceConfig
import com.netflix.appinfo.AmazonInfo
import com.netflix.appinfo.AmazonInfoConfig
import com.netflix.appinfo.ApplicationInfoManager
import com.netflix.appinfo.CloudInstanceConfig
import com.netflix.appinfo.EurekaInstanceConfig
import com.netflix.appinfo.InstanceInfo
import com.netflix.appinfo.MyDataCenterInstanceConfig
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider
import com.netflix.discovery.DefaultEurekaClientConfig
import com.netflix.discovery.DiscoveryClient
import com.netflix.discovery.EurekaClient
import com.netflix.discovery.EurekaClientConfig
import io.github.flaxoos.cloud.discovery.Service
import io.github.flaxoos.cloud.discovery.ServiceDiscoveryProvider
import io.github.flaxoos.cloud.discovery.ServiceId
import kotlin.properties.Delegates


class Eureka(val namespace: String) : ServiceDiscoveryProvider {

    val config = MyDataCenterInstanceConfig(namespace)
    val cloudInstanceConfig = CloudInstanceConfig(namespace)

    private var applicationInfoManager: ApplicationInfoManager by Delegates.notNull()
    private var eurekaClient: EurekaClient? = null

    init {
        val instanceConfig = CloudInstanceConfig(namespace)
        val instanceInfo = EurekaConfigBasedInstanceInfoProvider(instanceConfig).get()
        applicationInfoManager = ApplicationInfoManager(instanceConfig, instanceInfo)
    }


    override fun serviceStarting(name: String): ServiceId {
        eurekaClient = DiscoveryClient(applicationInfoManager, DefaultEurekaClientConfig(namespace))
    }

    override fun serviceStarted(id: ServiceId) {
        TODO("Not yet implemented")
    }

    override fun serviceStopping(id: ServiceId) {
        TODO("Not yet implemented")
    }

    override fun serviceStopped(id: ServiceId) {
        TODO("Not yet implemented")
    }

    override fun discoverServices(): List<Service> {
        TODO("Not yet implemented")
    }
}

class EurekaService(val service: Service) : AbstractInstanceConfig() {
    override fun getInstanceId(): String {
        service.id.toString()
    }

    override fun getAppname(): String {
        service.name
    }

    override fun getAppGroupName(): String {
        service.group
    }

    override fun getStatusPageUrlPath(): String {
        TODO("Not yet implemented")
    }

    override fun getStatusPageUrl(): String {
        TODO("Not yet implemented")
    }

    override fun getHomePageUrlPath(): String {
        TODO("Not yet implemented")
    }

    override fun getHomePageUrl(): String {
        TODO("Not yet implemented")
    }

    override fun getHealthCheckUrlPath(): String {
        TODO("Not yet implemented")
    }

    override fun getHealthCheckUrl(): String {
        TODO("Not yet implemented")
    }

    override fun getSecureHealthCheckUrl(): String {
        TODO("Not yet implemented")
    }

    override fun getDefaultAddressResolutionOrder(): Array<String> {
        TODO("Not yet implemented")
    }

    override fun getNamespace(): String {
        TODO("Not yet implemented")
    }
}

data class KtorAmazonInfoConfig(
    val namespace: String,
    val shouldLogAmazonMetadataErrors: Boolean,
    val readTimeout: Int,
    val connectTimeout: Int,
    val numRetries: Int,
    val shouldFailFastOnFirstLoad: Boolean,
    val shouldValidateInstanceId: Boolean
) : AmazonInfoConfig {
    override fun getNamespace(): String {
        return namespace
    }

    override fun shouldLogAmazonMetadataErrors(): Boolean {
        return shouldLogAmazonMetadataErrors
    }

    override fun getReadTimeout(): Int {
        return readTimeout
    }

    override fun getConnectTimeout(): Int {
        return connectTimeout
    }

    override fun getNumRetries(): Int {
        return numRetries
    }

    override fun shouldFailFastOnFirstLoad(): Boolean {
        return shouldFailFastOnFirstLoad
    }

    override fun shouldValidateInstanceId(): Boolean {
        return shouldValidateInstanceId
    }
}
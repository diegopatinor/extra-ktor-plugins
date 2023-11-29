package io.github.flaxoos.discovery.eureka

import com.netflix.appinfo.InstanceInfo
import com.netflix.appinfo.InstanceInfo.InstanceStatus.DOWN
import com.netflix.appinfo.InstanceInfo.InstanceStatus.OUT_OF_SERVICE
import com.netflix.appinfo.InstanceInfo.InstanceStatus.STARTING
import com.netflix.appinfo.InstanceInfo.InstanceStatus.UP
import io.github.flaxoos.cloud.discovery.DiscoverableService
import io.github.flaxoos.cloud.discovery.DiscoverableServiceInstance
import io.github.flaxoos.cloud.discovery.DiscoverableServiceId
import io.github.flaxoos.cloud.discovery.DiscoverableServiceInstanceId
import io.github.flaxoos.cloud.discovery.DiscoverableServiceName
import io.github.flaxoos.cloud.discovery.DiscoverableServiceName.Companion.asDiscoverableServiceName
import io.github.flaxoos.cloud.discovery.DiscoveryContext
import io.github.flaxoos.cloud.discovery.exceptions.ApplicationNotUpException
import io.github.flaxoos.discovery.eureka.config.EurekaClientConfiguration
import io.github.flaxoos.discovery.eureka.config.EurekaInstanceConfiguration
import io.ktor.client.HttpClient
import io.ktor.http.Url
import io.ktor.server.application.Application
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout


private const val APPLICATION_UP_WAIT_TIMEOUT_MS = 10000L
private const val APPLICATION_UP_RETRY_FREQ_MS = 1000L

internal class EurekaDiscoveryContext(
    override val application: Application,
    override val httpClient: HttpClient,
    val eurekaClientConfiguration: EurekaClientConfiguration? = null,
    val eurekaInstanceConfiguration: EurekaInstanceConfiguration? = null,
    namespace: String,
) : DiscoveryContext<EurekaServiceDiscoverer>() {

    override val serviceDiscoverer = EurekaServiceDiscoverer(
        application = application,
        eurekaClientConfiguration = eurekaClientConfiguration,
        eurekaInstanceConfiguration = eurekaInstanceConfiguration,
        namespace = namespace
    )

    override fun start() {
        with(serviceDiscoverer) {
            applicationInfoManager.setInstanceStatus(STARTING)
            waitForRegistrationWithEureka()
        }
    }

    override fun afterStarted() {
        serviceDiscoverer.applicationInfoManager.setInstanceStatus(UP)
    }

    override fun stop() {
        serviceDiscoverer.applicationInfoManager.setInstanceStatus(OUT_OF_SERVICE)
    }

    override fun afterStopped() {
        serviceDiscoverer.eurekaClient.shutdown()
        serviceDiscoverer.applicationInfoManager.setInstanceStatus(DOWN)
    }

    private fun EurekaServiceDiscoverer.waitForRegistrationWithEureka() {
        var applicationInstanceInfo: DiscoverableServiceInstance? = null
        runCatching {
            runBlocking {
                withTimeout(APPLICATION_UP_WAIT_TIMEOUT_MS) {
                    while (applicationInstanceInfo == null) {
                        applicationInstanceInfo = getNextApplicationInstance(
                            vipAddress = applicationInfoManager.info.vipAddress,
                            secure = applicationInfoManager.info.secure
                        )
                        if (applicationInstanceInfo == null) {
                            delay(APPLICATION_UP_RETRY_FREQ_MS)
                        } else break
                    }
                }
            }
        }.onFailure {
            if (it is TimeoutCancellationException) {
                throw ApplicationNotUpException
            } else throw it
        }
    }
}

internal class EurekaDiscoverableService(
    override val id: DiscoverableServiceId,
    override val tags: List<String>,
    override val meta: Map<String, String>
) : DiscoverableService {
    override val name: DiscoverableServiceName = id.value.asDiscoverableServiceName()
}

internal class EurekaDiscoverableServiceInstance(
    override val id: DiscoverableServiceInstanceId,
    override val serviceId: DiscoverableServiceId,
    override val hostName: String,
    override val port: Int,
    override val secure: Boolean,
    override val ipAddress: String,
    override val vipAddress: String,
    override val url: Url
) : DiscoverableServiceInstance

internal val InstanceInfo.secure: Boolean
    get() = this.isPortEnabled(InstanceInfo.PortType.SECURE)

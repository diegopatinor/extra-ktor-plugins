package io.github.flaxoos.discovery.eureka

import io.github.flaxoos.cloud.discovery.DiscoveryContext
import io.github.flaxoos.cloud.discovery.ServiceDiscoveryConfiguration
import io.github.flaxoos.cloud.discovery.ServiceDiscoveryDsl
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.cio.CIO
import io.ktor.server.application.Application

@ServiceDiscoveryDsl
public fun ServiceDiscoveryConfiguration.eureka(configuration: EurekaDiscoveryContextConfiguration.() -> Unit = {}) {
    contextConfiguration = EurekaDiscoveryContextConfiguration().apply(configuration)
}

@ServiceDiscoveryDsl
public class EurekaDiscoveryContextConfiguration : ServiceDiscoveryConfiguration.DiscoveryContextConfiguration() {
    private var clientConfiguration: (EurekaClientConfiguration.() -> Unit)? = null
    private var instanceConfiguration: (EurekaInstanceConfiguration.() -> Unit)? = null
    private var isAws: Boolean? = null
    public var namespace: String? = null

    @ServiceDiscoveryDsl
    public fun client(configuration: EurekaClientConfiguration.() -> Unit) {
        clientConfiguration = configuration
    }

    @ServiceDiscoveryDsl
    public fun instance(configuration: EurekaInstanceConfiguration.() -> Unit) {
        checkConfiguration()
        instanceConfiguration = configuration
        isAws = false
    }

    @ServiceDiscoveryDsl
    public fun awsInstance(configuration: EurekaInstanceConfiguration.() -> Unit) {
        checkConfiguration()
        instanceConfiguration = configuration
        isAws = true
    }

    private fun checkConfiguration() {
        require(isAws == null) { "Context can only be configured using instance or awsInstance, not both" }
    }

    @ServiceDiscoveryDsl
    public override fun provideContext(
        application: Application,
        httpClientConfig: HttpClientConfig<*>.() -> Unit
    ): DiscoveryContext<*> {
        require(namespace != null) { "Namespace must be set" }
        return EurekaDiscoveryContext(
            application = application,
            httpClient = HttpClient(CIO, httpClientConfig),
            namespace = namespace!!,
            eurekaClientConfiguration = clientConfiguration?.let {
                EurekaClientConfiguration(
                    namespace = namespace!!
                ).apply(it)
            },
            eurekaInstanceConfiguration = instanceConfiguration?.let {
                EurekaInstanceConfiguration(
                    isAws = with(checkNotNull(isAws) { "isAws wasn't set, this is a bug" }) { this },
                    namespace = namespace!!
                ).apply(it)
            }
        )
    }
}
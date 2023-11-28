package io.github.flaxoos.cloud.discovery

import io.github.flaxoos.cloud.discovery.DiscoveryContext.Companion.DiscoveryContextAttributeKey
import io.ktor.client.HttpClientConfig
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationPlugin
import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStarting
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.application.ApplicationStopping
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent

@DslMarker
public annotation class ServiceDiscoveryDsl

@ServiceDiscoveryDsl
public open class ServiceDiscoveryConfiguration {
    public lateinit var contextConfiguration: DiscoveryContextConfiguration
    public var type: ServiceDiscoveryServiceType = ServiceDiscoveryServiceType.Undefined
    private var httpClientConfig: HttpClientConfig<*>.() -> Unit = {}
    public fun client(config: HttpClientConfig<*>.() -> Unit) {
        httpClientConfig = config
    }

    @ServiceDiscoveryDsl
    public abstract class DiscoveryContextConfiguration {
        public abstract fun provideContext(
            application: Application,
            httpClientConfig: HttpClientConfig<*>.() -> Unit = {}
        ): DiscoveryContext<*>
    }
}

public sealed interface ServiceDiscoveryServiceType {
    public data object Undefined : ServiceDiscoveryServiceType
    public data object Server : ServiceDiscoveryServiceType
    public data class Client(val serviceName: String) : ServiceDiscoveryServiceType
}

public val ServiceDiscovery: ApplicationPlugin<ServiceDiscoveryConfiguration> =
    createApplicationPlugin("ServiceDiscovery", ::ServiceDiscoveryConfiguration) {

        val context = this.pluginConfig.contextConfiguration.provideContext(application)
        application.attributes.put(DiscoveryContextAttributeKey, context)

        on(MonitoringEvent(ApplicationStarting)) {
            context.start()
        }

        on(MonitoringEvent(ApplicationStarted)) {
            context.afterStarted()
        }

        on(MonitoringEvent(ApplicationStopping)) {
            context.afterStopped()
        }

        on(MonitoringEvent(ApplicationStopped)) {
            context.stop()
        }
    }

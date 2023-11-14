package io.github.flaxoos.cloud.discovery

import io.ktor.server.application.ApplicationStarted
import io.ktor.server.application.ApplicationStarting
import io.ktor.server.application.ApplicationStopped
import io.ktor.server.application.ApplicationStopping
import io.ktor.server.application.createApplicationPlugin
import io.ktor.server.application.hooks.MonitoringEvent
import io.ktor.server.application.log
import io.ktor.util.AttributeKey

@DslMarker
annotation class ServiceDiscoveryDsl

@ServiceDiscoveryDsl
class ServiceDiscoveryConfiguration {
    lateinit var provider: ServiceDiscoveryProvider
    var type: ServiceDiscoveryServiceType = ServiceDiscoveryServiceType.Undefined
}

sealed interface ServiceDiscoveryServiceType {
    data object Undefined : ServiceDiscoveryServiceType
    data object Server : ServiceDiscoveryServiceType
    data class Client(val serviceName: String) : ServiceDiscoveryServiceType
}

val ServiceDiscovery = createApplicationPlugin("ServiceDiscovery", ::ServiceDiscoveryConfiguration) {

    application.attributes.put(ServiceDiscoveryProviderAttributeKey, this.pluginConfig.provider)

    on(MonitoringEvent(ApplicationStarting)) { application ->
        val type = pluginConfig.type
        if (type is ServiceDiscoveryServiceType.Client) {
            val serviceId = this.pluginConfig.provider.serviceStarting(type.serviceName)
            application.attributes.put(ServiceIdAttributeKey, serviceId)
        }
    }

    on(MonitoringEvent(ApplicationStarted)) { application ->
        if (pluginConfig.type is ServiceDiscoveryServiceType.Client) {
            this.pluginConfig.provider.serviceStarted(application.attributes[ServiceIdAttributeKey])
        }
    }

    on(MonitoringEvent(ApplicationStopping)) { application ->
        if (pluginConfig.type is ServiceDiscoveryServiceType.Client) {
            this.pluginConfig.provider.serviceStopping(application.attributes[ServiceIdAttributeKey])
        }
    }

    on(MonitoringEvent(ApplicationStopped)) { application ->
        if (pluginConfig.type is ServiceDiscoveryServiceType.Client) {
            this.pluginConfig.provider.serviceStopped(application.attributes[ServiceIdAttributeKey])
        }
    }
}

internal val ServiceIdAttributeKey = AttributeKey<ServiceId>("ServiceId")
internal val ServiceDiscoveryProviderAttributeKey = AttributeKey<ServiceDiscoveryProvider>("ServiceDiscoveryProvider")
package io.github.flaxoos.cloud.discovery

interface ServiceDiscoveryProvider {
    fun serviceStarting(name: String): ServiceId

    fun serviceStarted(id: ServiceId)

    fun serviceStopping(id: ServiceId)

    fun serviceStopped(id: ServiceId)

    fun discoverServices(): List<Service>
}

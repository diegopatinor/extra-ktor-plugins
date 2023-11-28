package io.github.flaxoos.cloud.discovery

public interface ServiceDiscoverer {
    public fun getServices(): List<DiscoverableService>
    public fun getServiceInstances(serviceId: DiscoverableServiceId): List<DiscoverableServiceInstance>
    public fun getNextApplicationInstance(vipAddress: String, secure: Boolean): DiscoverableServiceInstance?
}
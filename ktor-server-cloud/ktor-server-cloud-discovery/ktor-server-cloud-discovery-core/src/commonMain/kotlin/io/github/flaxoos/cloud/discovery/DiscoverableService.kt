package io.github.flaxoos.cloud.discovery

import io.ktor.http.Url
import kotlin.jvm.JvmInline

public interface DiscoverableService {
    public val id: DiscoverableServiceId
    public val name: DiscoverableServiceName
    public val tags: List<String>
    public val meta: Map<String, String>
}

public interface DiscoverableServiceInstance {
    public val id: DiscoverableServiceInstanceId
    public val serviceId: DiscoverableServiceId
    public val hostName: String
    public val port: Int
    public val secure: Boolean
    public val ipAddress: String
    public val vipAddress: String
    public val url: Url
}

@JvmInline
public value class DiscoverableServiceId private constructor(public val value: String) {
    public companion object {
        public fun String.asDiscoverableServiceId(): DiscoverableServiceId = DiscoverableServiceId(this)
    }
}

@JvmInline
public value class DiscoverableServiceInstanceId private constructor(public val value: String) {
    public companion object {
        public fun String.asDiscoverableServiceInstanceId(): DiscoverableServiceInstanceId =
            DiscoverableServiceInstanceId(this)
    }
}

@JvmInline
public value class DiscoverableServiceName private constructor(public val value: String) {
    public companion object {
        public fun String.asDiscoverableServiceName(): DiscoverableServiceName = DiscoverableServiceName(this)
    }
}
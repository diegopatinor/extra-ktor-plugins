package io.github.flaxoos.cloud.discovery

import io.github.flaxoos.cloud.discovery.DiscoveryContext.Companion.DiscoveryContextAttributeKey
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.host
import io.ktor.client.request.port
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.HttpMethod.Companion.Delete
import io.ktor.http.HttpMethod.Companion.Get
import io.ktor.http.HttpMethod.Companion.Patch
import io.ktor.http.HttpMethod.Companion.Put
import io.ktor.server.application.Application
import io.ktor.util.AttributeKey

public val logger: KLogger = KotlinLogging.logger { }

@DslMarker
public annotation class DiscoveryClientDsl

@DiscoveryClientDsl
public suspend fun <T> Application.discovery(block: suspend DiscoveryContext<*>.() -> T): T =
    with(attributes[DiscoveryContextAttributeKey]) {
        block()
    }

@DiscoveryClientDsl
public suspend inline fun <reified T> DiscoveryContext<*>.get(
    vipAddress: String,
    secure: Boolean = false,
    noinline block: HttpRequestBuilder.() -> Unit = {}
): T {
    val response: T = serviceDiscoverer.getNextApplicationInstance(vipAddress, secure)?.let { app ->
        request(Get, app, block)
    } ?: error("No instance found for $vipAddress")
    return response
}

@DiscoveryClientDsl
public suspend inline fun <reified T> DiscoveryContext<*>.post(
    vipAddress: String,
    secure: Boolean = false,
    noinline block: HttpRequestBuilder.() -> Unit = {}
): T {
    return serviceDiscoverer.getNextApplicationInstance(vipAddress, secure)?.let { app ->
        request(HttpMethod.Post, app, block)
    } ?: error("No instance found for $vipAddress")
}

@DiscoveryClientDsl
public suspend inline fun <reified T> DiscoveryContext<*>.put(
    vipAddress: String,
    secure: Boolean = false,
    noinline block: HttpRequestBuilder.() -> Unit = {}
): T {
    return serviceDiscoverer.getNextApplicationInstance(vipAddress, secure)?.let { app ->
        request(Put, app, block)
    } ?: error("No instance found for $vipAddress")

}

@DiscoveryClientDsl
public suspend inline fun <reified T> DiscoveryContext<*>.delete(
    vipAddress: String,
    secure: Boolean = false,
    noinline block: HttpRequestBuilder.() -> Unit = {}
): T {
    return serviceDiscoverer.getNextApplicationInstance(vipAddress, secure)?.let { app ->
        request(Delete, app, block)
    } ?: error("No instance found for $vipAddress")
}

@DiscoveryClientDsl
public suspend inline fun <reified T> DiscoveryContext<*>.options(
    vipAddress: String,
    secure: Boolean = false,
    noinline block: HttpRequestBuilder.() -> Unit = {}
): T {
    return serviceDiscoverer.getNextApplicationInstance(vipAddress, secure)?.let { app ->
        request(HttpMethod.Options, app, block)
    } ?: error("No instance found for $vipAddress")
}

@DiscoveryClientDsl
public suspend inline fun <reified T> DiscoveryContext<*>.patch(
    vipAddress: String,
    secure: Boolean = false,
    block: HttpRequestBuilder.() -> Unit
): T {
    return serviceDiscoverer.getNextApplicationInstance(vipAddress, secure)?.let { app ->
        request(Patch, app, block)
    } ?: error("No instance found for $vipAddress")
}

@DiscoveryClientDsl
public suspend inline fun <reified T> DiscoveryContext<*>.request(
    method: HttpMethod,
    app: DiscoverableServiceInstance,
    block: HttpRequestBuilder.() -> Unit,
): T = httpClient.request(HttpRequestBuilder().apply(block).apply {
    this.method = method
    this.host = app.hostName
    this.port = app.port
}).body<T>()


public abstract class DiscoveryContext<DS : ServiceDiscoverer> {
    protected abstract val application: Application
    public abstract val httpClient: HttpClient
    public abstract val serviceDiscoverer: ServiceDiscoverer

    public abstract fun start()
    public abstract fun afterStarted()
    public abstract fun stop()
    public abstract fun afterStopped()

    public companion object {
        public val DiscoveryContextAttributeKey: AttributeKey<DiscoveryContext<out ServiceDiscoverer>> =
            AttributeKey("DiscoveryDelegate")
    }
}
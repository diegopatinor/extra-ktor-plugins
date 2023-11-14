package io.github.flaxoos.cloud.discovery

data class Service(
    val id: ServiceId,
    val name: String,
    val address: String,
    val port: Int,
    val tags: List<String> = emptyList(),
    val meta: Map<String, String> = emptyMap(),
)

interface ServiceId

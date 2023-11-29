package io.github.flaxoos.discovery.eureka.config

import io.ktor.server.config.ApplicationConfig

internal fun ApplicationConfig.configOrNull(path: String) = runCatching { config(path) }.getOrNull()

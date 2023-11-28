package io.github.flaxoos.discovery.eureka

import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.slf4j.helpers.NOPLogger

fun defaultServer(port: Int = 8080, module: Application.() -> Unit) = embeddedServer(CIO, environment = applicationEngineEnvironment {
    log = NOPLogger.NOP_LOGGER

    connector {
        this.port = port
    }

    module(module)
})
package io.github.flaxoos.discovery.eureka

import com.netflix.appinfo.ApplicationInfoManager
import com.netflix.appinfo.InstanceInfo
import com.netflix.appinfo.MyDataCenterInstanceConfig
import com.netflix.appinfo.providers.EurekaConfigBasedInstanceInfoProvider
import com.netflix.discovery.DefaultEurekaClientConfig
import com.netflix.discovery.DiscoveryClient
import com.netflix.discovery.EurekaClient
import com.netflix.discovery.internal.util.AmazonInfoUtils
import io.github.flaxoos.cloud.discovery.ServiceDiscovery
import io.github.flaxoos.cloud.discovery.discovery
import io.github.flaxoos.cloud.discovery.get
import io.github.flaxoos.discovery.eureka.config.EurekaInstanceConfiguration
import io.github.oshai.kotlinlogging.KotlinLogging
import io.kotest.assertions.fail
import io.kotest.assertions.withClue
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.path
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeoutOrNull
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName
import java.time.Duration
import kotlin.properties.Delegates
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

private val logger = KotlinLogging.logger { }

class ServiceDiscoveryEurekaPluginTest : FunSpec() {

    private val serviceResponseText = "hello"
    private val serviceName = "service"
    private val clientName = "client"
    private val serviceApplicationRequestPath = "service-path"
    private val clientApplicationRequestPath = "client-path"
    private val testNamespace = "test"
    private val commonVipAddress = "io.github.flaxoos"
    private val serviceApplicationPort = 8081
    private val clientApplicationPort = 8082
    private val serviceApplicationVipAddress = "$commonVipAddress.$serviceName"
    private val clientApplicationVipAddress = "$commonVipAddress.$clientName"
    private var eurekaClient: DiscoveryClient by Delegates.notNull()
    private var serviceApplication by Delegates.notNull<ApplicationEngine>()
    private var clientApplication by Delegates.notNull<ApplicationEngine>()

    init {
        beforeSpec {
            eurekaServer.start()
            eurekaClient = getEurekaDiscoveryClient("test-client")
        }
        afterTest {
            serviceApplication.stop()
            clientApplication.stop()
        }

        context("test service discovery") {
            withData(
                nameFn = { "Test service discovery ${if (it) "with aws instances" else ""}" },
                listOf(true, false)
            ) { isAws ->
                if (isAws) {
                    mockkStatic(AmazonInfoUtils::class)
                    every { AmazonInfoUtils.readEc2MetadataUrl(any(), any(), any(), any()) } returns "localhost"
                }
                serviceApplication = createApplication(
                    ApplicationConfig(
                        type = ApplicationType.Service,
                        name = serviceName,
                        port = serviceApplicationPort,
                        vipAddress = serviceApplicationVipAddress,
                        requestPath = serviceApplicationRequestPath,
                        isAwsInstance = isAws
                    )
                )
                clientApplication = createApplication(
                    ApplicationConfig(
                        type = ApplicationType.Client(serviceApplicationRequestPath, serviceName),
                        name = clientName,
                        port = clientApplicationPort,
                        vipAddress = clientApplicationVipAddress,
                        requestPath = clientApplicationRequestPath,
                        isAwsInstance = isAws
                    )
                ).start()
                serviceApplication.start()
                clientApplication.start()

                val serviceUpTimeout = SERVICE_AVAILABILITY_TIMEOUT.seconds
                eurekaClient.getNextInstanceInfo(serviceApplicationVipAddress, serviceUpTimeout)
                    ?: fail("No server instance found for $serviceApplicationVipAddress within $serviceUpTimeout")

                // Grace period for server availability in client context
                delay(SERVICE_AVAILABILITY_GRACE_MS.milliseconds)

                val httpClient = HttpClient(CIO)
                val response = httpClient.get {
                    url {
                        host = LOCALHOST
                        port = clientApplicationPort
                        path(clientApplicationRequestPath)
                    }
                }

                val responseText = response.bodyAsText()
                withClue(responseText) {
                    response.status shouldBe OK
                    responseText shouldBe serviceResponseText
                }
            }

        }
    }

    private fun createApplication(config: ApplicationConfig) = defaultServer(config.port) {
        val application = this
        install(ServiceDiscovery) {
            eureka {
                namespace = testNamespace
                client {
                    val clientRegion = "eu-central-1"
                    val clientRegionZone = "${clientRegion}a"
                    val defaultZone = "defaultZone"
                    val serviceUrl = "http://localhost:8080/eureka/v2/"
                    region = clientRegion
                    availabilityZones = mapOf(clientRegion to listOf(clientRegionZone))
                    eurekaServerServiceUrls =
                        mapOf(
                            defaultZone to listOf(serviceUrl),
                            clientRegionZone to listOf(serviceUrl)
                        )
                }
                if (config.isAwsInstance) {
                    awsInstance {
                        configureInstance(config.name, config.vipAddress, config.port)
                    }
                } else {
                    instance {
                        configureInstance(config.name, config.vipAddress, config.port)
                    }
                }
            }
        }
        routing {
            get(config.requestPath) {
                if (config.type is ApplicationType.Client) {
                    logger.info { "Client received request, requesting from service" }
                    val serviceResponse: String = application.discovery {
                        get("$commonVipAddress.${config.type.serviceName}") {
                            url {
                                path(config.type.serviceRequestPath)
                            }
                        }
                    }
                    call.respond(serviceResponse)
                } else {
                    logger.info { "Service received request, responding: $serviceResponseText" }
                    call.respond(serviceResponseText)
                }
            }
            healthAndStatusRoutes()
        }
        statusPages()
    }

    private data class ApplicationConfig(
        val type: ApplicationType,
        val name: String,
        val port: Int,
        val vipAddress: String,
        val requestPath: String,
        val isAwsInstance: Boolean
    )

    private sealed class ApplicationType {
        class Client(val serviceRequestPath: String, val serviceName: String) : ApplicationType()
        data object Service : ApplicationType()
    }

    private fun EurekaInstanceConfiguration.configureInstance(name: String, vipAddress: String, port: Int) {
        instanceId = "test-$name-instance"
        appname = "test-$name-app"
        appGroupName = "test-$name-app-group"
        asgName = "test-$name-asg-name"
        nonSecurePort = port
        virtualHostName = vipAddress
        statusPageUrl = "http://localhost:$port/status"
        healthCheckUrl = "http://localhost:$port/health"
        homePageUrl = "http://localhost:$port"
        leaseRenewalIntervalInSeconds = 1
        leaseExpirationDurationInSeconds = 120
        isInstanceEnabledOnit = false
        isNonSecurePortEnabled = true
    }

    private fun Routing.healthAndStatusRoutes() {
        get("health") {
            call.respond("OK")
        }
        get("status") {
            call.respond("UP")
        }
    }

    private fun Application.statusPages() {
        install(StatusPages) {
            exception<Throwable> { call, cause ->
                call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
            }
        }
    }

    private fun getEurekaDiscoveryClient(namespace: String): DiscoveryClient {
        val instanceConfig = MyDataCenterInstanceConfig(namespace)
        val instanceInfo = EurekaConfigBasedInstanceInfoProvider(instanceConfig).get()
        val applicationInfoManager = ApplicationInfoManager(instanceConfig, instanceInfo)
        return DiscoveryClient(applicationInfoManager, DefaultEurekaClientConfig(namespace))
    }

    companion object {
        private val eurekaServer =
            GenericContainer(DockerImageName.parse("netflixoss/eureka:1.3.1")).withCreateContainerCmdModifier { cmd ->
                cmd.withPlatform("linux/amd64")
            }
                .apply {
                    this.portBindings = listOf("8080:8080")
                }
                .waitingFor(
                    Wait.forLogMessage(Regex(".*The response status is 200.*").pattern, 1)
                ).withStartupTimeout(Duration.ofMinutes(3)).withReuse(true)

        private const val SERVICE_AVAILABILITY_GRACE_MS = 1000L
        private const val SERVICE_AVAILABILITY_TIMEOUT = 60
        private const val LOCALHOST = "localhost"
    }

    private suspend fun EurekaClient.getNextInstanceInfo(
        vipAddress: String,
        timeout: kotlin.time.Duration
    ): InstanceInfo? {
        return withTimeoutOrNull(timeout) {
            var instanceInfo: InstanceInfo? = null
            while (instanceInfo == null) {
                instanceInfo = try {
                    logger.info { "Test client checking for $vipAddress instance availability" }
                    getNextServerFromEureka(vipAddress, false)
                } catch (_: Exception) {
                    delay(1000)
                    null
                }
            }
            instanceInfo
        }
    }
}

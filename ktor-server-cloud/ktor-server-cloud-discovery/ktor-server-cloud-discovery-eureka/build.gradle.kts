import io.github.flaxoos.ktor.commonMainDependencies
import io.github.flaxoos.ktor.extensions.targetJvm
import io.github.flaxoos.ktor.jvmMainDependencies
import io.github.flaxoos.ktor.jvmTestDependencies

plugins {
    id("ktor-server-plugin-conventions")
}

kotlin {
    explicitApi()
    targetJvm()
    sourceSets {
        commonMainDependencies {
            implementation(projects.ktorServerCloud.ktorServerCloudDiscovery.ktorServerCloudDiscoveryCore)
        }
        jvmMainDependencies {
            implementation(libs.eureka.server)
            implementation(libs.eureka.server.governator)
            implementation(libs.eureka.client)
            implementation(libs.eureka.client.archaius2)
            implementation(libs.aws.java.sdk.core)
        }
        jvmTestDependencies {
            implementation(libs.ktor.server.cio.jvm)
            implementation(libs.ktor.server.call.logging.jvm)
            implementation(libs.kotest.extensions.testcontainers)
            implementation(libs.testcontainers.localstack)
        }
    }
}

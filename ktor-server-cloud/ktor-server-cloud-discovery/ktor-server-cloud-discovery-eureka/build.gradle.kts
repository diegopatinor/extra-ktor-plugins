import io.github.flaxoos.ktor.commonMainDependencies
import io.github.flaxoos.ktor.extensions.targetJvm
import io.github.flaxoos.ktor.jvmMainDependencies

plugins {
    id("ktor-server-plugin-conventions")
}

kotlin {
    targetJvm()
    sourceSets {
        commonMainDependencies {
            implementation(projects.ktorServerCloud.ktorServerCloudDiscovery.ktorServerCloudDiscoveryCore)
        }
        jvmMainDependencies {
            implementation(libs.eureka.server)
            implementation(libs.eureka.client)
        }
    }
}

import io.github.flaxoos.ktor.commonMainDependencies
import io.github.flaxoos.ktor.extensions.shadowJvmJar
import io.github.flaxoos.ktor.extensions.targetJvm
import io.github.flaxoos.ktor.extensions.targetNative
import io.github.flaxoos.ktor.jvmTestDependencies
import io.github.flaxoos.ktor.nativeMainDependencies
import kotlinx.atomicfu.plugin.gradle.AtomicFUTransformTask
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("ktor-server-plugin-conventions")
}

kotlin {
    explicitApi()
    targetJvm()
    targetNative()
    sourceSets {
        commonMainDependencies {
            api(projects.common)
            api(projects.ktorServerTaskScheduling.ktorServerTaskSchedulingCore)
            implementation(projects.ktorServerTaskScheduling.ktorServerTaskSchedulingRedis.redisClient)
        }
        jvmTestDependencies {
            implementation(projects.ktorServerTaskScheduling.ktorServerTaskSchedulingCore.test)
            implementation(libs.mockk)
            implementation(libs.testcontainers.redis)
            implementation(libs.kreds)
        }
        nativeMainDependencies {
            api(projects.common)
            api(projects.ktorServerTaskScheduling.ktorServerTaskSchedulingCore)
        }
    }
}

tasks.withType(DokkaTask::class).configureEach {
    dependsOn(projects.ktorServerTaskScheduling.ktorServerTaskSchedulingCore.dependencyProject.tasks.shadowJvmJar)
}

tasks.withType(AtomicFUTransformTask::class).configureEach {
    dependsOn(projects.ktorServerTaskScheduling.ktorServerTaskSchedulingCore.dependencyProject.tasks.shadowJvmJar)
}

import io.github.flaxoos.ktor.commonMainDependencies
import io.github.flaxoos.ktor.extensions.targetJvm
import io.github.flaxoos.ktor.extensions.targetNative

plugins {
    id("ktor-server-plugin-conventions")
}

kotlin {
    targetJvm()
    targetNative()
    sourceSets {
        commonMainDependencies {
        }
    }
}

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.4.30" // kotlin_version
    kotlin("plugin.serialization") version "1.4.30" // kotlin_version
    `maven-publish`
}

group = "br.com.devsrsouza.kotlin.docker"
version = "1.0.0"

val kotlin_version = "1.4.30"
val coroutines_version = "1.3.8"
val serialization_version = "1.1.0"
val ktor_version = "1.5.3"

repositories {
    mavenCentral()
}

kotlin {
    jvm()
//    ios { binaries { framework { freeCompilerArgs += "-Xobjc-generics" } } }
//    js {
//        browser()
//        nodejs()
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serialization_version")
                api("io.ktor:ktor-client-core:$ktor_version")
                api("io.ktor:ktor-client-json:$ktor_version")
                api("io.ktor:ktor-client-serialization:$ktor_version")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.ktor:ktor-client-mock:$ktor_version")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk7"))
                implementation("io.ktor:ktor-client-okhttp:$ktor_version")
                implementation("com.kohlschutter.junixsocket:junixsocket-native-common:2.0.4")

            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("io.ktor:ktor-client-mock-jvm:$ktor_version")
            }
        }

//        val iosMain by getting {
//            dependencies {
//                api("io.ktor:ktor-client-ios:$ktor_version")
//            }
//        }
//
//        val iosTest by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-mock-native:$ktor_version")
//            }
//        }
//
//        val jsMain by getting {
//            dependencies {
//                api("io.ktor:ktor-client-js:$ktor_version")
//            }
//        }
//
//        val jsTest by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-mock-js:$ktor_version")
//                implementation("io.ktor:ktor-client-js:$ktor_version")
//            }
//        }

        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.Experimental")
            }
        }
    }
}

tasks {
    register("iosTest") {
        val device = project.findProperty("device")?.toString() ?: "iPhone 8"
        dependsOn("linkDebugTestIosX64")
        group = JavaBasePlugin.VERIFICATION_GROUP
        description = "Execute unit tests on ${device} simulator"
        doLast {
            val binary = kotlin.targets.getByName<KotlinNativeTarget>("iosX64").binaries.getTest("DEBUG")
            exec {
                commandLine("xcrun", "simctl", "spawn", device, binary.outputFile)
            }
        }
    }
}

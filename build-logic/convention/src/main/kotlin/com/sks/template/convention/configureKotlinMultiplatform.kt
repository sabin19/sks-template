package com.sks.template.convention

import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalWasmDsl::class, ExperimentalKotlinGradlePluginApi::class)
internal fun Project.configureKotlinMultiplatform(
    extension: KotlinMultiplatformExtension
) = extension.apply {
    jvmToolchain(21)

    androidTarget()
    jvm("desktop")
    iosArm64()
    iosSimulatorArm64()
    js {
        browser()
    }
    wasmJs {
        browser()
    }

    applyDefaultHierarchyTemplate {
        common {
            group("web") {
                withJs()
                withWasmJs()
            }
        }
    }

    sourceSets.apply {
        commonMain {
            dependencies {
                implementation(libs.findLibrary("koin-core").get())
                implementation(libs.findLibrary("kotlinx-coroutines").get())
            }
        }

        androidMain {
            dependencies {
                implementation(libs.findLibrary("koin-android").get())
            }
        }

        named("desktopMain") {
            dependencies {
                implementation(libs.findLibrary("kotlinx-coroutines-swing").get())
            }
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

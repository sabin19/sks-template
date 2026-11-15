plugins {
    alias(libs.plugins.sks.application.multiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(libs.bundles.ktor)
            implementation(libs.kotlinx.serialization.json)
            api(projects.core.common)
            implementation(projects.core.model)
            implementation(projects.core.datastore)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        webMain.dependencies {
            implementation(libs.ktor.client.js)
        }
        desktopMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
    }
}

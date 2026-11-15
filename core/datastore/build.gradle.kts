plugins {
    alias(libs.plugins.sks.application.multiplatform)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
        }
        commonMain.dependencies {
            api(libs.datastore.preferences.core)
            api(libs.datastore.core)
            api(projects.core.model)
        }
        webMain.dependencies {
            implementation(libs.datastore.core.okio)
        }
    }
}

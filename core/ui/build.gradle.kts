plugins {
    alias(libs.plugins.sks.application.multiplatform)
    alias(libs.plugins.sks.multiplatform.compose)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
        }
        commonMain.dependencies {
            implementation(projects.core.designsystem)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor3)
        }
    }
}

plugins {
    alias(libs.plugins.sks.application.multiplatform)
    alias(libs.plugins.sks.multiplatform.compose)
    alias(libs.plugins.sks.application.feature)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.coil.compose)
        }
    }
}

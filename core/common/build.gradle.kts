plugins {
    alias(libs.plugins.sks.application.multiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines)
        }
    }
}

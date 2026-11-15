plugins {
    alias(libs.plugins.sks.application.multiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.runtime)
            implementation(libs.foundation)
            implementation(libs.jetbrains.ui)
            implementation(libs.components.resources)
            implementation(libs.ui.tooling.preview)
            implementation(libs.material3)
            implementation(libs.material.icons.extended)
            implementation(libs.material3.window.size)
            implementation(libs.coil.compose)
            api(projects.core.model)
        }
    }
}

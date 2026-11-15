plugins {
    `kotlin-dsl`
}

group = "com.sks.template.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("kotlinMultiplatform") {
            id = "sks.application.multiplatform"
            implementationClass = "KotlinMultiplatformPlugin"
        }
        register("composeMultiplatformPlugin") {
            id = "sks.multiplatform.compose"
            implementationClass = "ComposeMultiplatformPlugin"
        }
        register("featurePlugin") {
            id = "sks.application.feature"
            implementationClass = "FeaturePlugin"
        }
    }
}

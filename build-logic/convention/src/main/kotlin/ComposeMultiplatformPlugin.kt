import com.sks.template.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposeMultiplatformPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("jetbrainsCompose").get().get().pluginId)
            apply(libs.findPlugin("compose-compiler").get().get().pluginId)
        }

        val composeDependencies = extensions.getByType<ComposeExtension>().dependencies
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(composeDependencies.runtime)
                        implementation(composeDependencies.foundation)
                        implementation(composeDependencies.material3)
                        implementation(composeDependencies.materialIconsExtended)
                        implementation(composeDependencies.ui)
                        implementation(composeDependencies.components.resources)
                        implementation(composeDependencies.components.uiToolingPreview)
                        implementation(libs.findLibrary("navigation-compose").get())
                        implementation(libs.findLibrary("koin-compose").get())
                        implementation(libs.findLibrary("koin-compose-viewmodel").get())
                    }
                }
            }
        }
    }
}

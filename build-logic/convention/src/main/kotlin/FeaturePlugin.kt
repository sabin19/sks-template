import com.sks.template.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.findPlugin("kotlinSerialization").get().get().pluginId)
        }
        extensions.configure<KotlinMultiplatformExtension> {
            sourceSets.apply {
                commonMain {
                    dependencies {
                        implementation(project(":core:designsystem"))
                        implementation(project(":core:ui"))
                        implementation(project(":core:domain"))
                        implementation(project(":core:common"))
                        implementation(project(":core:model"))
                        implementation(libs.findLibrary("kotlinx-serialization-json").get())
                    }
                }
            }
        }
    }
}

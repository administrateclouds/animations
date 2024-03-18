rootProject.name = "Animations"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":api")
include(":core")

include(":platform:spigot")
include(":platform:spigot:adapters:provider")
include(":platform:spigot:adapters:nms:r1_20_4_R1")


pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}
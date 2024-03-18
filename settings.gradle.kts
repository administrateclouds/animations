rootProject.name = "Animations"

include(":api")
include(":paper")

include(":adapters:provider")
include(":adapters:nms:1_20_4_R1")


pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    includeBuild("build-logic")
}
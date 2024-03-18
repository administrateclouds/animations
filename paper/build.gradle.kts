plugins {
    id("cloud.polars.animations.shadow-conventions")
}

dependencies {
    compileOnly(variantOf(libs.easyarmorstands.api) { classifier("all") }) {
        isTransitive = false
    }
    compileOnly(libs.paper.api)
    implementation(libs.joml)
    implementation(libs.cloud.paper)
    implementation(libs.cloud.annotations)
    implementation(libs.cloud.minecraft.extras)
    implementation(libs.adventure.platform.bukkit)
    implementation(libs.adventure.text.minimessage)
    implementation(libs.adventure.text.serializer.gson)
    implementation(libs.adventure.text.serializer.legacy)
    implementation(project(":api"))
    implementation(project(":adapters:provider"))
    implementation(project(":adapters:nms:1_20_4_R1"))
}

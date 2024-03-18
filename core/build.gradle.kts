plugins {
    id("cloud.polars.animations.publish-conventions")
}

dependencies {
    compileOnly(libs.joml)
    compileOnly(libs.cloud.core)
    compileOnly(libs.cloud.annotations)
    compileOnly(libs.cloud.minecraft.extras)
    compileOnly(libs.adventure.api)
    compileOnly(libs.adventure.text.minimessage)
    compileOnly(libs.adventure.text.serializer.gson)
    compileOnly(libs.adventure.text.serializer.legacy)
    implementation(projects.api)
}
plugins {
    id("java")
    alias(libs.plugins.paperweight.userdev)
    alias(libs.plugins.shadow)
}

group = "cloud.polars"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://ci.mg-dev.eu/plugin/repository/everything")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)
    compileOnly(variantOf(libs.easyarmorstands.api) { classifier("all") }) {
        isTransitive = false
    }
    implementation(project(":animations-api"))
    implementation(libs.joml)
    implementation(libs.cloud.paper)
    implementation(libs.cloud.annotations)
    implementation(libs.cloud.minecraft.extras)
    implementation(libs.adventure.platform.bukkit)
    implementation(libs.adventure.text.minimessage)
    implementation(libs.adventure.text.serializer.gson)
    implementation(libs.adventure.text.serializer.legacy)
}

tasks.test {
    useJUnitPlatform()
}
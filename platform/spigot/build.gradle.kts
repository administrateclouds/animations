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
    implementation(projects.core)
    implementation(projects.platform.spigot.adapters.provider)
    implementation(project(":platform:spigot:adapters:nms:r1_20_4_R1"))
}

application {
    mainClass.set("cloud.polars.animations.spigot.AnimationsSpigot")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("Animations-Spigot")
}
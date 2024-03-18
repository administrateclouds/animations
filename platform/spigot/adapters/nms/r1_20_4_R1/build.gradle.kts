plugins {
    id("cloud.polars.animations.paperweight-conventions")
}

dependencies {
    compileOnly(libs.paper.api)
    implementation(projects.platform.spigot.adapters.provider)
    paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
}
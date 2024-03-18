plugins {
    id("cloud.polars.animations.java-conventions")
    id("io.papermc.paperweight.userdev")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
}
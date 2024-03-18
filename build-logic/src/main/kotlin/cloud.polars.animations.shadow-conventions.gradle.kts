import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("cloud.polars.animations.java-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        // relocations here
        // relocate("tld.domain.package", "cloud.polars.animations.libs")
    }

    build {
        dependsOn(shadowJar)
    }
}
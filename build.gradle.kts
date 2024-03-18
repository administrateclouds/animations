plugins {
    `java-library`
    eclipse
    id("cloud.polars.animations.build-logic")
}

allprojects {
    group = properties["group"] as String + "." + properties["id"] as String
    version = properties["version"] as String
    description = properties["description"] as String
}

var platforms = setOf(
    projects.platform.spigot
).map { it.dependencyProject }

subprojects {
    apply {
        plugin("java-library")
        plugin("cloud.polars.animations.build-logic")
    }

    when(this) {
        in platforms -> plugins.apply("cloud.polars.animations.platform-conventions")
        else -> plugins.apply("cloud.polars.animations.java-conventions")
    }
}
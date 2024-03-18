import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `java-library`
    `maven-publish`
}

val libs = the<LibrariesForLibs>()

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://ci.mg-dev.eu/plugin/repository/everything")
}

group = properties["group"] as String
version = properties["version"] as String
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}

tasks {
    processResources {
        filesMatching(listOf("plugin.yml")) {
            expand(
                "id" to properties["id"],
                "name" to properties["pluginName"],
                "version" to properties["version"],
                "paperApiVersion" to parseApiVersion(libs.versions.paper.get()),
                "description" to properties["description"],
                "url" to properties["url"],
                "authors" to properties["authors"]
            )
        }
    }
}

fun parseApiVersion(input: String): String {
    val regex = Regex("""^(\d+\.\d+)""")
    val matchResult = regex.find(input)
    val version = matchResult?.groupValues?.get(1)

    return version ?: "unknown"
}
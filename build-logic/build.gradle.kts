plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.shadow)
    implementation(libs.paperweight)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
plugins {
    kotlin("jvm") version "1.7.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.0"
}

dependencies {
    implementation(projects.useCases)
    implementation(projects.commonLib)
}

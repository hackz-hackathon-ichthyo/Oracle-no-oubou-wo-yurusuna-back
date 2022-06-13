rootProject.name = "movie-server"
pluginManagement {
    repositories {
        gradlePluginPortal()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    ":interfaces",
    ":common-lib",
    ":use-cases",
    ":infrastructure",
    ":database",
)
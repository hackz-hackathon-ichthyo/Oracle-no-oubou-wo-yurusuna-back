@Suppress("DSL_SCOPE_VIOLATION") plugins {
    application
}
application {
    mainClass.set("io.ktor.server.cio.EngineMain")
}
tasks {
    jar {
        manifest {
            attributes(
                mapOf(
                    "Main-Class" to "io.ktor.server.cio.EngineMain",
                    "ImplementationTitle" to project.name,
                    "Implementation-Version" to project.version
                )
            )
        }
    }

}

dependencies {
    implementation(projects.useCases)
    implementation(projects.commonLib)
}

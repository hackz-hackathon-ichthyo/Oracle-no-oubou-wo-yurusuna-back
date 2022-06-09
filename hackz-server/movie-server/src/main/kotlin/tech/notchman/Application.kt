package tech.notchman

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import tech.notchman.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSecurity()
        configureHTTP()
        configureSerialization()
        configureSockets()
    }.start(wait = true)
}

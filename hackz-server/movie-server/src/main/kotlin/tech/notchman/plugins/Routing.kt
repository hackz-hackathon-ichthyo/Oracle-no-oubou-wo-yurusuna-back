package tech.notchman.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.autohead.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import tech.notchman.controllers.ApiController
import tech.notchman.infra.ApiClient

fun Application.configureRouting() {
    install(AutoHeadResponse)
    val apiClient = ApiClient()
    val apiController = ApiController(apiClient)
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/progress") {
            call.respond(apiController.getProgress())
        }

    }
}

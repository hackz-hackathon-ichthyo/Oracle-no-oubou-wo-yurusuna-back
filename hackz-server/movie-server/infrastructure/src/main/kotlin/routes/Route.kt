package routes


import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

@KtorExperimentalLocationsAPI
fun Routing.root() {
    val userController: UserController by inject()

    get<UserParam> { param ->
        call.respond(userController.getUser(param.userId))
    }

    route("v1") {
        route("/users") {
            get<UserParam> { param ->
                call.respond(userController.getUser(param.userId))
            }
        }
    }
}

/**
 * Locations
 */
@KtorExperimentalLocationsAPI
@Location("/{userId}")
data class UserParam(val userId: Long)

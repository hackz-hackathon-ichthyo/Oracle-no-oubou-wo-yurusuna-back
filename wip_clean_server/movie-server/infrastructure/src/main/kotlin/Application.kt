import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import mapper.ObjectMapperBuilder
import module.KoinModuleBuilder
import module.KoinModuleBuilder.modules
import org.koin.core.context.startKoin
import routes.root
import javax.xml.transform.OutputKeys.INDENT

@KtorExperimentalLocationsAPI
fun Application.main() {

    startKoin{
        modules(KoinModuleBuilder.modules())
    }
//    install(ContentNegotiation) {
//        jackson {
//            ObjectMapperBuilder.build(this)
//            configure(INDENT, true)
//            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//        }
//    }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if(cause is AuthorizationException) {
                call.respondText(text = "403: $cause" , status = HttpStatusCode.Forbidden)
            } else {
                call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
            }
        }
    }
    install(Locations)
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        anyHost()
    }

    routing {
        root()
    }
}
class AuthorizationException(override val message: String?) : Throwable()
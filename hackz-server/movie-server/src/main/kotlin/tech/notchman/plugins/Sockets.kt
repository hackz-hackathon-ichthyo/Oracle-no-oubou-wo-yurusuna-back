package tech.notchman.plugins

import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration
import io.ktor.server.application.*
import io.ktor.server.routing.*
import tech.notchman.repository.ChatRepository

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    val chatRepository = ChatRepository();

    routing {
        webSocket("/rooms/{chat_id}") { // websocketSession
            val chatId = call.parameters["chat_id"]
            outgoing.send(Frame.Text("YOUR CHAT ROOM: $chatId"))

            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        val text = frame.readText()
                        outgoing.send(Frame.Text("YOU SAID: $text"))
                        if (text.equals("bye", ignoreCase = true)) {
                            close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }
}

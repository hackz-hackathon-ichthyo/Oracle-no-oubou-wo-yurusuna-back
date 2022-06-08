package tech.notchman.example_server

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    @GetMapping("/demo")

    fun index(model: Model): String {
        model.addAttribute("message", "Hello World!")
        return "index"
    }
}
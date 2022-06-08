package tech.notchman.example_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExampleServerApplication

fun main(args: Array<String>) {
    runApplication<ExampleServerApplication>(*args)
}

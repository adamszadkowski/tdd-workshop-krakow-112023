package pl.allegro.tdd

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GreetingApplication

fun main(args: Array<String>) {
    createSpringApplication().run(*args)
}

fun createSpringApplication(): SpringApplication =
    SpringApplication(GreetingApplication::class.java)

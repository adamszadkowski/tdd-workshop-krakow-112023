package pl.allegro.tdd.api

import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicReference

@RestController
@RequestMapping("/greeting")
class GreetingEndpoint {
    private val message = AtomicReference("hello world")

    @GetMapping
    fun getGreeting(): GreetingResponse {
        return GreetingResponse(message.get())
    }

    @PutMapping
    fun updateGreeting(@RequestBody request: UpdateRequest): GreetingResponse {
        return GreetingResponse(message.updateAndGet { request.message })
    }
}

data class GreetingResponse(
    val message: String,
)

data class UpdateRequest(
    val message: String,
)

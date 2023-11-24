package pl.allegro.tdd.api

import org.springframework.web.bind.annotation.*
import pl.allegro.tdd.domain.GreetingService

@RestController
@RequestMapping("/greeting")
class GreetingEndpoint(
    private val greetingService: GreetingService,
) {

    @GetMapping
    fun getGreeting(): GreetingResponse {
        return GreetingResponse(greetingService.get())
    }

    @PutMapping
    fun updateGreeting(@RequestBody request: UpdateRequest): GreetingResponse {
        return GreetingResponse(greetingService.update(request.message))
    }
}

data class GreetingResponse(
    val message: String,
)

data class UpdateRequest(
    val message: String,
)

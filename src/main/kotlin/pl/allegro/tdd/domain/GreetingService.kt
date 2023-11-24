package pl.allegro.tdd.domain

import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference("hello world")

    fun get(): String {
        return message.get()
    }

    fun update(message: String): String {
        return this.message.updateAndGet { message }
    }
}

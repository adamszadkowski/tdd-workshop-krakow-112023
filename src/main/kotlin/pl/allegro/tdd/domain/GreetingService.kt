package pl.allegro.tdd.domain

import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference("hello world")

    fun get(): String {
        return message.get()
    }

    fun update(message: String): String {
        if (message.isEmpty()) throw InvalidMessageException()
        else if (message.length >= 100) throw InvalidMessageException()
        return this.message.updateAndGet { message }
    }

    class InvalidMessageException : RuntimeException()
}

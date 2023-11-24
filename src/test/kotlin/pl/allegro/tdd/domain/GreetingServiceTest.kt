package pl.allegro.tdd.domain

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess

class GreetingServiceTest {
    private val service = GreetingService()

    @Test
    fun `update message`() {
        expectThat(service.update("new message"))
            .isEqualTo("new message")
            .isEqualTo(service.get())
    }

    @Nested
    inner class `validations` {

        @ParameterizedTest
        @ValueSource(ints = [0, 100, 101])
        fun `do not update incorrect message`(length: Int) {
            service.update("before")

            expectThrows<GreetingService.InvalidMessageException> {
                service.update("a".repeat(length))
            }
            expectThat(service.get()).isEqualTo("before")
        }

        @ParameterizedTest
        @ValueSource(ints = [1, 2, 98, 99])
        fun `update on correct message`(length: Int) {
            val message = "a".repeat(length)

            expectCatching {
                service.update(message)
            }.isSuccess()
            expectThat(service.get()).isEqualTo(message)
        }
    }
}

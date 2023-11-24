package pl.allegro.tdd

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.doesNotContain
import strikt.assertions.isFalse
import strikt.assertions.isTrue

class PasswordValidatorTest {

    @Nested
    inner class `password length` {

        @ParameterizedTest
        @ValueSource(ints = [0, 4])
        fun `fail on too short`(length: Int) {
            expectThat(describePassword("a".repeat(length))).contains("too short")
        }

        @ParameterizedTest
        @ValueSource(ints = [5, 6])
        fun `pass on correct size`(length: Int) {
            expectThat(describePassword("1".repeat(length))).doesNotContain("too short")
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class `digits in password` {

        @Test
        fun `fail on missing digit`() {
            expectThat(describePassword("")).contains("no digit")
        }

        @ParameterizedTest
        @MethodSource("getDigits")
        fun `pass on exising digit`(digit: String) {
            expectThat(describePassword(digit)).doesNotContain("no digit")
        }

        val digits = (0..9).map { it.toString() }.toList()
    }

    @Test
    fun `validate password`() {
        expectThat(validatePassword("")).isFalse()
        expectThat(validatePassword("12345")).isTrue()
    }

    private fun validatePassword(password: String): Boolean {
        return describePassword(password).isEmpty()
    }

    private fun describePassword(password: String): List<String> {
        val description = mutableListOf<String>()

        if (password.length < 5) description += "too short"
        if (!password.contains("[0-9]".toRegex())) description += "no digit"

        return description
    }
}

interface PasswordDescriptor {
    fun describe(): List<String>
}

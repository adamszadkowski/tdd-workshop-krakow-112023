package pl.allegro.tdd.elves

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

class CaloriesCounterTest {
    private var input = ""
    private val counter = CaloriesCounter { input }

    @Test
    fun `empty elves are empty`() {
        expectThat(counter.count()).isNull()
    }

    @Nested
    inner class `single elf` {

        @Test
        fun `single item`() {
            input = """
                1000
            """.trimIndent()

            expectThat(counter.count()).isEqualTo(1000)
        }

        @Test
        fun `two items`() {
            input = """
                1000
                2000
            """.trimIndent()

            expectThat(counter.count()).isEqualTo(3000)
        }
    }

    @Nested
    inner class `two elves` {

        @Test
        fun `single item each`() {
            input = """
                1000

                2000
            """.trimIndent()

            expectThat(counter.count()).isEqualTo(2000)
        }

        @Test
        fun `two items each`() {
            input = """
                1000
                500

                2000
                300
            """.trimIndent()

            expectThat(counter.count()).isEqualTo(2300)
        }
    }

    @Nested
    inner class `order by calories` {

        @Test
        fun `highest calories are first`() {
            input = """
                1000

                2000
            """.trimIndent()

            expectThat(counter.count()).isEqualTo(2000)
        }
    }
}

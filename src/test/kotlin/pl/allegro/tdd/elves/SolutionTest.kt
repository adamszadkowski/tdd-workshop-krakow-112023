package pl.allegro.tdd.elves

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SolutionTest {

    @Test
    fun `find elf with max calories`() {
        val provider = ResourceContentProvider("puzzle-input.txt")
        val counter = CaloriesCounter(provider)

        expectThat(counter.count()).isEqualTo(69177)
    }
}

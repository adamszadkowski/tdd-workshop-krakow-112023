package pl.allegro.tdd.elves

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ResourceContentProviderTest {
    private val provider: ContentProvider = ResourceContentProvider("sample.txt")

    @Test
    fun `can create`() {
        expectThat(provider.provide()).isEqualTo(
            """
                this is content
                of sample.txt
            """.trimIndent()
        )
    }
}

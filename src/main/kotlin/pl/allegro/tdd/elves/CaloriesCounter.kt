package pl.allegro.tdd.elves

class CaloriesCounter(
    private val provider: ContentProvider,
) {

    fun count(): Int? =
        provider
            .provide()
            .split("\n\n")
            .filter { it.isNotEmpty() }
            .maxOfOrNull { elf ->
                elf.split("\n")
                    .sumOf { it.toIntOrNull() ?: 0 }
            }
}

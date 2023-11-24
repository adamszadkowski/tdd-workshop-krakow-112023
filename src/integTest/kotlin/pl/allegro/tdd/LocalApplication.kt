package pl.allegro.tdd

fun main(args: Array<String>) {
    createSpringApplication()
        .apply {
            setAdditionalProfiles("local")
        }
        .run(*args)
}

package pl.allegro.tdd.elves

class ResourceContentProvider(
    private val resource: String,
) : ContentProvider {
    override fun provide(): String =
        ResourceContentProvider::class.java.classLoader
            .getResource(resource).readText()
}

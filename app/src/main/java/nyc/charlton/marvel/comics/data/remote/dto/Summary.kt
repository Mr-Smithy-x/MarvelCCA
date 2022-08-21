package nyc.charlton.marvel.comics.data.remote.dto

sealed class Summary(
    val resourceURI: String,
    val name: String
) {
    class Series(resourceURI: String, name: String): Summary(resourceURI, name)
    class Comic(resourceURI: String, name: String): Summary(resourceURI, name)
    class Event(resourceURI: String, name: String): Summary(resourceURI, name)
    class Story(resourceURI: String, name: String, val type: String): Summary(resourceURI, name)
    class Character(resourceURI: String, name: String, val role: String): Summary(resourceURI, name)
    class Creator(resourceURI: String, name: String, val role: String): Summary(resourceURI, name)
}

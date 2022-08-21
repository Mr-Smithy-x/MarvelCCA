package nyc.charlton.marvel.comics.data.remote.dto

sealed class List<T>(
    val available: Int,
    val returned: Int,
    val collectionURI: String,
    val items: Array<T>
) {

    class Character(
        available: Int,
        returned: Int,
        collectionURI: String,
        items: Array<Summary.Character>
    ): List<Summary.Character>(available, returned, collectionURI, items)

    class Creator(
        available: Int,
        returned: Int,
        collectionURI: String,
        items: Array<Summary.Creator>
    ): List<Summary.Creator>(available, returned, collectionURI, items)

    class Story(
        available: Int,
        returned: Int,
        collectionURI: String,
        items: Array<Summary.Story>
    ): List<Summary.Story>(available, returned, collectionURI, items)

    class Event(
        available: Int,
        returned: Int,
        collectionURI: String,
        items: Array<Summary.Event>
    ): List<Summary.Event>(available, returned, collectionURI, items)

}

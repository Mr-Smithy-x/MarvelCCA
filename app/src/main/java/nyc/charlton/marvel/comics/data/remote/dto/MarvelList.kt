package nyc.charlton.marvel.comics.data.remote.dto

sealed class MarvelList<T>(
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
    ): MarvelList<Summary.Character>(available, returned, collectionURI, items)

    class Creator(
        available: Int,
        returned: Int,
        collectionURI: String,
        items: Array<Summary.Creator>
    ): MarvelList<Summary.Creator>(available, returned, collectionURI, items)

    class Story(
        available: Int,
        returned: Int,
        collectionURI: String,
        items: Array<Summary.Story>
    ): MarvelList<Summary.Story>(available, returned, collectionURI, items)

    class Event(
        available: Int,
        returned: Int,
        collectionURI: String,
        items: Array<Summary.Event>
    ): MarvelList<Summary.Event>(available, returned, collectionURI, items)

}

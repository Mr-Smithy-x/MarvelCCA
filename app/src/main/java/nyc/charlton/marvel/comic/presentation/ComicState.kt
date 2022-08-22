package nyc.charlton.marvel.comic.presentation

import nyc.charlton.marvel.comics.domain.model.Comic

data class ComicState(
    val loading: Boolean = false,
    val comic: Comic? = null,
    val error: String = ""
) {

}
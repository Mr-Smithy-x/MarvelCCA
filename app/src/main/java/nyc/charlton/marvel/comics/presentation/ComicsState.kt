package nyc.charlton.marvel.comics.presentation

import nyc.charlton.marvel.comics.domain.model.Comic

data class ComicsState(
    val loading: Boolean = false,
    val comics: List<Comic> = emptyList(),
    val error: String = ""
) {

}
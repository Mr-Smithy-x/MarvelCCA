package nyc.charlton.marvel.comics.presentation

import nyc.charlton.marvel.comics.domain.model.Comic

data class ComicsState(
    val loading: Boolean = false,
    val comics: List<Comic> = emptyList(),
    val error: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ComicsState) return false

        if (loading != other.loading) return false
        if (comics != other.comics) return false
        if (error != other.error) return false

        return true
    }

    override fun hashCode(): Int {
        var result = loading.hashCode()
        result = 31 * result + comics.hashCode()
        result = 31 * result + error.hashCode()
        return result
    }
}
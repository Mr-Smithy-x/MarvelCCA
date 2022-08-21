package nyc.charlton.marvel.comics.data.repository

import nyc.charlton.marvel.comics.domain.model.Comic
import nyc.charlton.marvel.common.Resource

interface ComicRepository {

    suspend fun getComics(): Resource<List<Comic>>

    suspend fun getComicById(comicId: Int): Resource<Comic>

}
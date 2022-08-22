package nyc.charlton.marvel.comics.domain.repository

import nyc.charlton.marvel.comics.domain.model.Comic
import nyc.charlton.marvel.common.Resource

interface ComicRepository {

    suspend fun getComics(): Resource<List<Comic>>

    suspend fun getComicById(comicId: Int): Resource<Comic>

    suspend fun getLatestComics(): Resource<List<Comic>>

}
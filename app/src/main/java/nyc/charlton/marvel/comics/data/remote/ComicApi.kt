package nyc.charlton.marvel.comics.data.remote

import nyc.charlton.marvel.comics.data.remote.dto.ComicDTO
import nyc.charlton.marvel.common.ComicDataContainer
import nyc.charlton.marvel.common.DataContainer
import nyc.charlton.marvel.common.Resource
import retrofit2.http.GET

interface ComicApi {

    @GET("/v1/public/comics") suspend fun getComics(): Resource<ComicDataContainer> //Fetches lists of comics.
    @GET("/v1/public/comics/{comicId}") suspend fun getComic(comicId: Int): Resource<ComicDataContainer>// Fetches a single comic by id.
    //@GET("/v1/public/comics/{comicId}/characters") suspend fun getComicCharacters(comicId: Int): Resource<Any>// Fetches lists of characters filtered by a comic id.
    //@GET("/v1/public/comics/{comicId}/creators") suspend fun getComicCreators(comicId: Int): Resource<Any> // Fetches lists of creators filtered by a comic id.
    //@GET("/v1/public/comics/{comicId}/events") suspend fun getComicEvents(comicId: Int): Resource<Any> // Fetches lists of events filtered by a comic id.
    //@GET("/v1/public/comics/{comicId}/stories") suspend fun getComicStories(comicId: Int ): Resource<Any>// Fetches lists of stories filtered by a comic id.

}
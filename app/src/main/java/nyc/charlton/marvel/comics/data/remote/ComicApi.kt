package nyc.charlton.marvel.comics.data.remote

import nyc.charlton.marvel.comics.data.remote.dto.ComicDTO
import nyc.charlton.marvel.common.ComicDataContainer
import nyc.charlton.marvel.common.DataContainer
import nyc.charlton.marvel.common.Resource
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicApi {

    @GET("/v1/public/comics?orderBy=title&format=comic&noVariants=true") suspend fun getComics(): Resource<DataContainer<ComicDTO>> //Fetches lists of comics.
    @GET("/v1/public/comics/{comicId}") suspend fun getComic(comicId: Int): Resource<DataContainer<ComicDTO>>// Fetches a single comic by id.
    @GET("/v1/public/comics?format=comic&noVariants=true") suspend fun getLatestComics(): Resource<DataContainer<ComicDTO>>
    @GET("/v1/public/comics?orderBy=title&format=comic&noVariants=true") suspend fun searchComics(@Query("title") query: String): Resource<DataContainer<ComicDTO>>
    //@GET("/v1/public/comics/{comicId}/characters") suspend fun getComicCharacters(comicId: Int): Resource<Any>// Fetches lists of characters filtered by a comic id.
    //@GET("/v1/public/comics/{comicId}/creators") suspend fun getComicCreators(comicId: Int): Resource<Any> // Fetches lists of creators filtered by a comic id.
    //@GET("/v1/public/comics/{comicId}/events") suspend fun getComicEvents(comicId: Int): Resource<Any> // Fetches lists of events filtered by a comic id.
    //@GET("/v1/public/comics/{comicId}/stories") suspend fun getComicStories(comicId: Int ): Resource<Any>// Fetches lists of stories filtered by a comic id.

}
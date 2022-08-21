package nyc.charlton.marvel.comics.domain.repository

import nyc.charlton.marvel.comics.data.remote.ComicApi
import nyc.charlton.marvel.comics.data.remote.dto.toComic
import nyc.charlton.marvel.comics.data.repository.ComicRepository
import nyc.charlton.marvel.comics.domain.model.Comic
import nyc.charlton.marvel.common.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val api: ComicApi
) : ComicRepository {
    override suspend fun getComics(): Resource<List<Comic>> {
        return try {
            val comics = api.getComics()
            Resource.Success(comics.data?.results?.map { it.toComic() } ?: emptyList())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server")
        }
    }

    override suspend fun getComicById(comicId: Int): Resource<Comic> {
        return try {
            val comics = api.getComics()
            val comic = comics.data?.results?.get(0)?.toComic()
            if (comic != null) {
                return Resource.Success(comic)
            } else {
                return Resource.Error("Comic not found")
            }
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server")
        }
    }

}
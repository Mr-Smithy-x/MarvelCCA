package nyc.charlton.marvel.comics.data.repository

import nyc.charlton.marvel.comics.data.local.ComicDao
import nyc.charlton.marvel.comics.data.local.entity.toComic
import nyc.charlton.marvel.comics.data.remote.ComicApi
import nyc.charlton.marvel.comics.data.remote.dto.toComic
import nyc.charlton.marvel.comics.data.remote.dto.toComicEntity
import nyc.charlton.marvel.comics.domain.model.Comic
import nyc.charlton.marvel.comics.domain.repository.ComicRepository
import nyc.charlton.marvel.common.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ComicRepositoryImpl @Inject constructor(
    private val api: ComicApi,
    private val dao: ComicDao
) : ComicRepository {
    override suspend fun getComics(): Resource<List<Comic>> {
        return try {
            val comics = api.getComics()
            Resource.Success(comics.data?.results?.map {
                dao.insert(it.toComicEntity())
                it.toComic()
            } ?: emptyList())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server")
        }
    }

    override suspend fun getComicById(comicId: Int): Resource<Comic> {
        return try {
            val comic = dao.getComic(comicId)
            if (comic != null) {
                return Resource.Success(comic.toComic())
            }
            val comics = api.getComic(comicId)
            val comicEntity = comics.data?.results?.get(0)?.toComicEntity()
            if (comicEntity != null) {
                return Resource.Success(comicEntity.toComic())
            } else {
                return Resource.Error("Comic not found")
            }
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server")
        }
    }

    override suspend fun getLatestComics(): Resource<List<Comic>> {
        return try {
            val comics = api.getLatestComics()
            Resource.Success(comics.data?.results?.map {
                dao.insert(it.toComicEntity())
                it.toComic()
            } ?: emptyList())
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "Unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn't reach server")
        }
    }

}
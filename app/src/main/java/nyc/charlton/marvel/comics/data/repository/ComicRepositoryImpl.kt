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
            val comics = dao.getComics("")
            if (comics.isNotEmpty()) {
                return Resource.Success(comics.map {
                    it.toComic()
                })
            }
            Resource.Error(e.localizedMessage ?: "Unexpected error occurred")
        } catch (e: IOException) {
            val comics = dao.getComics("")
            if (comics.isNotEmpty()) {
                return Resource.Success(comics.map {
                    it.toComic()
                })
            }
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
            val results = comics.data?.results
            val comicEntity = results?.find { it.id == comicId }?.toComicEntity()
            if (comicEntity != null) {
                dao.insert(comicEntity)
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
            val comics = dao.getComics("")
            if (comics.isNotEmpty()) {
                return Resource.Success(comics.map {
                    it.toComic()
                })
            }
            Resource.Error(e.localizedMessage ?: "Unexpected error occurred")
        } catch (e: IOException) {
            val comics = dao.getComics("")
            if (comics.isNotEmpty()) {
                return Resource.Success(comics.map {
                    it.toComic()
                })
            }
            Resource.Error("Couldn't reach server")
        }
    }

    override suspend fun searchComics(query: String): Resource<List<Comic>> {
        return try {
            val comics = api.searchComics(query)

            val list: List<Comic>? = comics.data?.results?.map {
                dao.insert(it.toComicEntity())
                it.toComic()
            }
            if (list?.isEmpty() == true) {
                Resource.Success(dao.getComics(query).map { it.toComic() } ?: emptyList())
            }else {
                Resource.Success(list ?: emptyList())
            }
        } catch (e: HttpException) {
            val comics = dao.getComics(query)
            if (comics.isNotEmpty()) {
                return Resource.Success(comics.map {
                    it.toComic()
                })
            }
            Resource.Error(e.localizedMessage ?: "Unexpected error occurred")
        } catch (e: IOException) {
            val comics = dao.getComics(query)
            if (comics.isNotEmpty()) {
                return Resource.Success(comics.map {
                    it.toComic()
                })
            }
            Resource.Error("Couldn't reach server")
        }
    }

}
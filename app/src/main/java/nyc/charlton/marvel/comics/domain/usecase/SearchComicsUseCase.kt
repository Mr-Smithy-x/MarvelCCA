package nyc.charlton.marvel.comics.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nyc.charlton.marvel.comics.domain.model.Comic
import nyc.charlton.marvel.comics.domain.repository.ComicRepository
import nyc.charlton.marvel.common.Resource
import javax.inject.Inject

class SearchComicsUseCase @Inject constructor(
    private val repository: ComicRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Comic>>> = flow {
        emit(Resource.Loading())
        val comics = repository.searchComics(query)
        emit(comics) // comics could be of type success or error at this point
    }
}
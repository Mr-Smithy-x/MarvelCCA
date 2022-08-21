package nyc.charlton.marvel.comics.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nyc.charlton.marvel.comics.data.repository.ComicRepository
import nyc.charlton.marvel.comics.domain.model.Comic
import nyc.charlton.marvel.common.Resource
import javax.inject.Inject

class GetComicUseCase @Inject constructor(
    private val repository: ComicRepository
) {
    operator fun invoke(comicId: Int): Flow<Resource<Comic>> = flow {
        emit(Resource.Loading())
        val comics = repository.getComicById(comicId)
        emit(comics) // comics could be of type success or error at this point
    }
}
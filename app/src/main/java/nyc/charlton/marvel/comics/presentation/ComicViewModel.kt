package nyc.charlton.marvel.comics.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import nyc.charlton.marvel.comics.data.repository.ComicRepository
import nyc.charlton.marvel.comics.domain.usecase.GetComicsUseCase
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val repository: ComicRepository,
    private val comicsUseCase: GetComicsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
}
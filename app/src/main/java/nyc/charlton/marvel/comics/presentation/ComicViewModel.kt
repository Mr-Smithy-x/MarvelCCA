package nyc.charlton.marvel.comics.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import nyc.charlton.marvel.comics.data.repository.ComicRespository
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val repository: ComicRespository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
}
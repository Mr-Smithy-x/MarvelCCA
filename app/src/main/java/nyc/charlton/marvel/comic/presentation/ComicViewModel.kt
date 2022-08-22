package nyc.charlton.marvel.comic.presentation

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nyc.charlton.marvel.comic.domain.usecase.GetComicUseCase
import nyc.charlton.marvel.common.Resource
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    application: Application,
    private val comicsUseCase: GetComicUseCase
): AndroidViewModel(application) {

    private val _vm = MutableLiveData<ComicState>()
    val state: LiveData<ComicState> = _vm

    init {
        /*savedStateHandle.get<Int>(Constants.PARAM_COMIC_ID)?.let{ comicId ->
            getComic(comicId)
        }*/
    }

    fun getComicById(comicId: Int) {
        comicsUseCase(comicId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _vm.value = ComicState(false, result.data)
                }
                is Resource.Error -> {
                    _vm.value = ComicState(error = result.status?:"An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _vm.value = ComicState(loading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    class Factory(
        val application: Application,
        private val usecase: GetComicUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                Application::class.java,
                GetComicUseCase::class.java
            ).newInstance(application, usecase)
        }
    }

}
package nyc.charlton.marvel.comics.presentation

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import nyc.charlton.marvel.comics.domain.usecase.GetComicsUseCase
import nyc.charlton.marvel.comics.domain.usecase.GetLatestComicsUseCase
import nyc.charlton.marvel.comics.domain.usecase.SearchComicsUseCase
import nyc.charlton.marvel.common.Resource
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val newThisMonthUseCase: GetComicsUseCase,
    private val latestComicsUseCase: GetLatestComicsUseCase,
    private val searchComicsUseCase: SearchComicsUseCase
) : ViewModel() {

    private val _newThisMonth = MutableLiveData<ComicsState?>()
    val newThisMonth: LiveData<ComicsState?> get() = _newThisMonth

    private val _latestComic = MutableLiveData<ComicsState?>(null)
    val latestComics: LiveData<ComicsState?> get() = _latestComic

    init {
        getComics()
        getLatestComics()
    }

    fun getComics() {
        newThisMonthUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _newThisMonth.postValue(ComicsState(false, result.data ?: emptyList()))
                }
                is Resource.Error -> {
                    _newThisMonth.postValue(
                        ComicsState(error = result.status ?: "An unexpected error occurred"))
                }
                is Resource.Loading -> {
                    _newThisMonth.postValue(ComicsState(loading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getLatestComics() {
        latestComicsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _latestComic.postValue(ComicsState(false, result.data ?: emptyList()))
                }
                is Resource.Error -> {
                    _latestComic.postValue(ComicsState(error = result.status ?: "An unexpected error occurred"))
                }
                is Resource.Loading -> {
                    _latestComic.postValue(ComicsState(loading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchComics(search: String) {
        searchComicsUseCase(search).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _newThisMonth.postValue(ComicsState(false, result.data ?: emptyList()))
                }
                is Resource.Error -> {
                    _newThisMonth.postValue(ComicsState(
                        error = result.status ?: "An unexpected error occurred"
                    ))
                }
                is Resource.Loading -> {
                    _newThisMonth.postValue(ComicsState(loading = true))
                }
            }
        }.launchIn(viewModelScope)
    }

    class Factory(
        val application: Application,
        private val getComicsUseCase: GetComicsUseCase,
        private val getLatestComicsUseCase: GetLatestComicsUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                Application::class.java,
                GetComicsUseCase::class.java,
                GetLatestComicsUseCase::class.java
            ).newInstance(application, getComicsUseCase, getLatestComicsUseCase)
        }
    }
}
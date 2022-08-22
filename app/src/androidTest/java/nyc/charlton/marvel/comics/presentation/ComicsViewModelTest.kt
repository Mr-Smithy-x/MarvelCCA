package nyc.charlton.marvel.comics.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import nyc.charlton.marvel.comics.domain.usecase.GetComicsUseCase
import nyc.charlton.marvel.comics.domain.usecase.GetLatestComicsUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ComicsViewModelTest {

    @get: Rule
    val rule = HiltAndroidRule(this)

    lateinit var vm: ComicsViewModel

    @Inject
    lateinit var getComicsUseCaseTest: GetComicsUseCase

    @Inject
    lateinit var getLatestComicsUseCase: GetLatestComicsUseCase
    //@get: Rule
    //var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        rule.inject()
        vm = ComicsViewModel(getComicsUseCaseTest, getLatestComicsUseCase)
    }

    @Test
    fun verifyViewModelResultFirstLoadsThenEmitsResponse() {
        while(vm.latestComics.value == null);
        assert(vm.latestComics.value!!.loading) {
            "When receiving first value, it should be loading"
        }
        while (vm.latestComics.value!!.loading);
        assert(vm.latestComics.value!!.comics.isNotEmpty()) {
            "When receiving the next value it should have the result"
        }

    }

}
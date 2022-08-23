package nyc.charlton.marvel.comic.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import nyc.charlton.marvel.comic.domain.usecase.GetComicUseCase
import nyc.charlton.marvel.comics.data.local.ComicDao
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ComicViewModelTest {

    @get: Rule
    val rule = HiltAndroidRule(this)

    lateinit var vm: ComicViewModel

    @Inject
    lateinit var getComicsUseCaseTest: GetComicUseCase

    @Inject
    lateinit var dao: ComicDao

    //@get: Rule
    //var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        rule.inject()
        vm = ComicViewModel(getComicsUseCaseTest)
    }

    @Test
    fun verifyViewModelResultFirstLoadsThenEmitsResponseWithCorrectId() {
        vm.getComicById(95748)
        while (vm.state.value == null);
        assert(vm.state.value!!.loading) {
            "When receiving first value, it should be loading"
        }

        while (vm.state.value!!.loading);
        val comic = vm.state.value!!.comic
        assert(comic != null) {
            "When receiving the next value it should have the result"
        }
        assert(comic?.id == 95748) {
            "Something went wrong: ${comic?.id}"
        }

    }

    @Test
    fun verifyDaoHasIdAfterBeingAddedViaApi() = runBlocking {
        vm.getComicById(95748)
        while (vm.state.value == null);
        while (vm.state.value!!.loading);

        val comic = dao.getComic(95748)
        assert(comic != null) {
            "Comic is suppose to be null"
        }
        assert(comic?.id == 95748) {
            "Id Should Match"
        }
    }


    @Test
    fun verifyFetchingHasFailedWithoutApiResponse() = runBlocking {
        val comic = dao.getComic(95748)
        assert(comic == null) {
            "Comic is suppose to be null"
        }
    }

}
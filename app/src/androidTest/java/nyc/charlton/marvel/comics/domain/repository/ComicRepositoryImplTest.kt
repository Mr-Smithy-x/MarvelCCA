package nyc.charlton.marvel.comics.domain.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ComicRepositoryImplTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Inject
    lateinit var repo: ComicRepository

    @Before
    fun setup() {
        rule.inject()
    }

    @Test
    fun verifyValidResponseFromRepository() = runBlocking {
        val latestComics = repo.getLatestComics()
        assert(latestComics.data?.isNotEmpty() == true)
    }


}
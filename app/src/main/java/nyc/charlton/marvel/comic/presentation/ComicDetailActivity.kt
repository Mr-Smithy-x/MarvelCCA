package nyc.charlton.marvel.comic.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import nyc.charlton.marvel.R
import nyc.charlton.marvel.comics.domain.model.Comic

@AndroidEntryPoint
class ComicDetailActivity : AppCompatActivity() {


    private val comicViewModel: ComicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val comicId: Int = intent.getIntExtra(ARG_COMIC, 0)
        if (comicId == 0) {
            finish()
            return
        }
        setContentView(R.layout.activity_comic_detail)
        comicViewModel.state.observe(this) { result ->
            val comic = result.comic
            when {
                !result.loading && comic != null -> {

                }
                !result.loading && result.error.isNotEmpty() -> {
                    finish()
                    return@observe
                }
                else -> {
                    // Loading...
                }
            }
        }
        comicViewModel.getComicById(comicId)
    }

    companion object {
        const val ARG_COMIC: String = "ARG_COMIC"
        fun start(context: Context, comic: Comic) {
            val intent = Intent(context, ComicDetailActivity::class.java)
            intent.putExtra(ARG_COMIC, comic.id)
            context.startActivity(intent)
        }
    }
}
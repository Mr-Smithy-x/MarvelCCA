package nyc.charlton.marvel.comic.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import nyc.charlton.marvel.R
import nyc.charlton.marvel.comics.domain.model.Comic
import nyc.charlton.marvel.databinding.ActivityComicDetailBinding

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
        val binding = ActivityComicDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        comicViewModel.state.observe(this) { result ->
            val comic = result.comic
            when {
                !result.loading && comic != null -> {
                    binding.activityComicDetailTitle.text = comic.title
                    binding.activityComicDetailDesc.text = comic.description?:comic.variantDescription
                    binding.activityComicDetailCardImg.load(comic.thumbnail.toURL())
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

fun AppCompatImageView.load(toURL: String) {
    Glide.with(this.context).load(toURL).into(this)
}

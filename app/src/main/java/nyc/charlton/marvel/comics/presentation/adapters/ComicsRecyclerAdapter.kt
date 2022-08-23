package nyc.charlton.marvel.comics.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import nyc.charlton.marvel.R
import nyc.charlton.marvel.comic.presentation.ComicDetailActivity
import nyc.charlton.marvel.comics.domain.model.Comic
import nyc.charlton.marvel.comics.presentation.ComicsState

class ComicsRecyclerAdapter(
    lifecycleOwner: LifecycleOwner,
    liveData: LiveData<ComicsState>,
    val viewType: Int = VIEW_TYPE_ROW
) :
    RecyclerView.Adapter<ComicsRecyclerAdapter.ComicViewHolder>() {

    private var result: ComicsState? = null

    init {
        liveData.observe(lifecycleOwner) { result ->
            this.result = result
            notifyDataSetChanged()
        }
    }

    companion object {
        const val VIEW_TYPE_COLUMN = 0
        const val VIEW_TYPE_ROW = 1
        private const val VIEW_TYPE_LOADING = 2
        private const val VIEW_TYPE_ERROR = 3
        private const val VIEW_TYPE_NO_RESULTS = 4
    }

    sealed class ComicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        open fun setComic(comic: Comic) {}
        fun setMessage(message: String) {
            this.itemView.findViewById<AppCompatTextView>(R.id.view_comic_state).text = message
        }

        class Column(view: View) : ComicViewHolder(view) {

            override fun setComic(comic: Comic) {
                itemView.findViewById<AppCompatTextView>(R.id.view_comic_title).text = comic.title
                itemView.findViewById<AppCompatTextView>(R.id.view_comic_author).text = comic.issn
                Glide.with(itemView.context).load(comic.thumbnail.toURL()).into(itemView.findViewById<AppCompatImageView>(R.id.view_comic_img))
                itemView.setOnClickListener {
                    ComicDetailActivity.start(it.context, comic)
                }
            }

        }

        class Row(view: View) : ComicViewHolder(view) {

            override fun setComic(comic: Comic) {
                itemView.findViewById<AppCompatTextView>(R.id.view_comic_title).text = comic.title
                itemView.findViewById<AppCompatTextView>(R.id.view_comic_desc).text = comic.description
                Glide.with(itemView.context).load(comic.thumbnail.toURL()).into(itemView.findViewById<AppCompatImageView>(R.id.view_comic_img))
                itemView.setOnClickListener {
                    ComicDetailActivity.start(it.context, comic)
                }
            }

        }

        class Loading(view: View) : ComicViewHolder(view) {

        }

        class Error(view: View) : ComicViewHolder(view) {

        }

        class NoResult(view: View) : ComicViewHolder(view) {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_COLUMN -> ComicViewHolder.Column(
                inflater.inflate(
                    R.layout.view_comic_column,
                    parent,
                    false
                )
            )

            VIEW_TYPE_ROW -> ComicViewHolder.Row(
                inflater.inflate(
                    R.layout.view_comic_row,
                    parent,
                    false
                )
            )

            VIEW_TYPE_LOADING -> ComicViewHolder.Loading(
                inflater.inflate(
                    R.layout.view_comic_state,
                    parent,
                    false
                )
            )
            VIEW_TYPE_ERROR -> ComicViewHolder.Error(
                inflater.inflate(
                    R.layout.view_comic_state,
                    parent,
                    false
                )
            )

            else -> ComicViewHolder.NoResult(
                inflater.inflate(
                    R.layout.view_comic_state,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            result?.loading == true -> VIEW_TYPE_LOADING
            result?.error?.isNotEmpty() == true -> VIEW_TYPE_ERROR
            result?.comics?.isNotEmpty() == true -> viewType
            else -> VIEW_TYPE_NO_RESULTS
        }
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        when {
            result?.loading == true -> {
                holder.setMessage("Loading....")
            }
            result?.comics?.isNotEmpty() == true -> {
                result?.comics?.get(position)?.let { comic ->
                    holder.setComic(comic)
                }
            }
            result?.error?.isNotEmpty() == true -> {
                holder.setMessage(result?.error?:"Something went wrong")
            }
        }
    }

    override fun getItemCount(): Int {
        return result?.comics?.size ?: 1
    }
}
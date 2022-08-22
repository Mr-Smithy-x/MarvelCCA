package nyc.charlton.marvel.comics.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import nyc.charlton.marvel.R
import nyc.charlton.marvel.comics.presentation.adapters.ComicsRecyclerAdapter
import javax.inject.Inject

@AndroidEntryPoint
class ComicsFragment : Fragment(R.layout.fragment_comics) {


    private val vm: ComicsViewModel by viewModels()

    private lateinit var columnAdapter: ComicsRecyclerAdapter
    private lateinit var rowAdapter: ComicsRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        columnAdapter = ComicsRecyclerAdapter(this, vm.latestComics, ComicsRecyclerAdapter.VIEW_TYPE_COLUMN)
        rowAdapter = ComicsRecyclerAdapter(this, vm.newThisMonth, ComicsRecyclerAdapter.VIEW_TYPE_ROW)
        val comicsRV = view.findViewById<RecyclerView>(R.id.fragment_comics_rv_column_comics)
        val latestComicsRV = view.findViewById<RecyclerView>(R.id.fragment_comics_rv_row_comics)
        comicsRV.adapter = columnAdapter
        latestComicsRV.adapter = rowAdapter
    }

    companion object {
        fun newInstance() = ComicsFragment()
    }

}
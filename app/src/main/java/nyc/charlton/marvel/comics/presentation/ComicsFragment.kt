package nyc.charlton.marvel.comics.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import nyc.charlton.marvel.R
import nyc.charlton.marvel.comics.presentation.adapters.ComicsRecyclerAdapter

@AndroidEntryPoint
class ComicsFragment : Fragment(R.layout.fragment_comics), SearchView.OnQueryTextListener {


    private val vm: ComicsViewModel by viewModels()

    private lateinit var columnAdapter: ComicsRecyclerAdapter
    private lateinit var rowAdapter: ComicsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        columnAdapter = ComicsRecyclerAdapter(this, vm.latestComics, ComicsRecyclerAdapter.VIEW_TYPE_COLUMN)
        rowAdapter = ComicsRecyclerAdapter(this, vm.newThisMonth, ComicsRecyclerAdapter.VIEW_TYPE_ROW)
        val comicsRV = view.findViewById<RecyclerView>(R.id.fragment_comics_rv_column_comics)
        val latestComicsRV = view.findViewById<RecyclerView>(R.id.fragment_comics_rv_row_comics)
        comicsRV.adapter = columnAdapter
        latestComicsRV.adapter = rowAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val item: MenuItem = menu.findItem(R.id.search_option)
        val actionView: SearchView = item.actionView as SearchView
        actionView.setOnQueryTextListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            if(it.length >= 2){
                vm.searchComics(it)
                return true
            }
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
    companion object {
        fun newInstance() = ComicsFragment()
    }

}
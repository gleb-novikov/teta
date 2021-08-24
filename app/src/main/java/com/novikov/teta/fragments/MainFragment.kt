package com.novikov.teta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.novikov.teta.R
import com.novikov.teta.adapters.MoviesAdapter
import com.novikov.teta.movies.MovieDto
import com.novikov.teta.movies.MoviesDataSourceImpl
import com.novikov.teta.movies.MoviesModel
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var moviesList: MutableList<MovieDto>
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var recyclerMovies: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        moviesList = MoviesModel(MoviesDataSourceImpl()).getMovies().toMutableList()
        swipeRefresh = this.requireView().findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener { viewModel.refreshMovies() }

        moviesAdapter = MoviesAdapter(viewModel::navigateToMovie)
        recyclerMovies = this.requireView().findViewById(R.id.recyclerMovies)
        recyclerMovies.adapter = moviesAdapter

        viewModel.navController = requireView().findNavController()
        viewModel.dataList.observe(this, Observer(moviesAdapter::initData))
        viewModel.viewState.observe(this, Observer(::render))

        viewModel.loadMovies()
    }

    data class ViewState(
        val isDownloaded: Boolean = false
    )

    private fun render(viewState: ViewState) = with(viewState) {
        swipeRefresh.isRefreshing = isDownloaded
    }
}

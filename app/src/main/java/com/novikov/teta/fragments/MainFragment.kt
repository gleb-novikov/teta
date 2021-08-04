package com.novikov.teta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.novikov.teta.R
import com.novikov.teta.adapters.MoviesAdapter
import com.novikov.teta.movies.MovieDto
import com.novikov.teta.movies.MoviesDataSourceImpl
import com.novikov.teta.movies.MoviesModel
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class MainFragment : Fragment() {
    private lateinit var moviesList: MutableList<MovieDto>
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
        initFragment()
    }

    private fun initFragment() {
        moviesList = MoviesModel(MoviesDataSourceImpl()).getMovies().toMutableList()
        recyclerMovies = this.requireView().findViewById(R.id.recyclerMovies)
        recyclerMovies.adapter = MoviesAdapter(moviesList, this::showToast)
        swipeRefresh = this.requireView().findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener { refreshMovies() }
    }

    private fun showToast(title: String) {
        Toast.makeText(this.context, title, Toast.LENGTH_SHORT).show()
        requireView().findNavController().navigate(R.id.action_mainFragment_to_movieFragment)
    }

    private fun refreshMovies() {
        GlobalScope.launch() {
            withContext(Dispatchers.IO) {
                Thread.sleep(2000)
                moviesList.shuffle()
            }
            withContext(Dispatchers.Main) {
                recyclerMovies.adapter?.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }
}
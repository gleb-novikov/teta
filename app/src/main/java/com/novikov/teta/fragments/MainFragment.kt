package com.novikov.teta.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.novikov.teta.R
import com.novikov.teta.adapters.MoviesAdapter
import com.novikov.teta.movies.MoviesDataSourceImpl
import com.novikov.teta.movies.MoviesModel

class MainFragment : Fragment() {
    private lateinit var movieList: MoviesModel
    private lateinit var recyclerMovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        movieList = MoviesModel(MoviesDataSourceImpl())
        recyclerMovies = this.requireView().findViewById(R.id.recyclerMovies)
        recyclerMovies.adapter = MoviesAdapter(movieList.getMovies(), this::showToast)
    }

    private fun showToast(title: String) {
        Toast.makeText(this.context, title, Toast.LENGTH_SHORT).show()
        requireView().findNavController().navigate(R.id.action_mainFragment_to_movieFragment)
    }
}
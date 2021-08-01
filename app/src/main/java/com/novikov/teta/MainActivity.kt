package com.novikov.teta

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.novikov.teta.adapters.MoviesAdapter
import com.novikov.teta.movies.MoviesDataSourceImpl
import com.novikov.teta.movies.MoviesModel

class MainActivity : AppCompatActivity() {
    private lateinit var movieList: MoviesModel
    private lateinit var recyclerMovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivity()
    }

    private fun initActivity() {
        movieList = MoviesModel(MoviesDataSourceImpl())
        recyclerMovies = findViewById(R.id.recyclerMovies)
        recyclerMovies.adapter = MoviesAdapter(movieList.getMovies(), this::showToast)
    }

    private fun showToast(title: String) {
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
    }
}

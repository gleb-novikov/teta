package com.novikov.teta.activity

import androidx.lifecycle.ViewModel
import com.novikov.teta.database.AppDatabase
import com.novikov.teta.movies.MoviesDataSourceImpl
import com.novikov.teta.movies.MoviesModel

class ActivityViewModel : ViewModel() {
    fun initCurrentMoviesToDB() {
        val db = AppDatabase.instance
        if (db.movieDao().getAll().isEmpty()) {
            val model = MoviesModel(MoviesDataSourceImpl())
            for (movie in model.getMovies()) {
                db.movieDao().insert(movie)
            }
        }
    }
}

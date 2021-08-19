package com.novikov.teta.movies

import com.novikov.teta.database.Movie

interface MoviesDataSource {
    fun getMovies(): MutableList<Movie>

    fun randomizeMovies()
}
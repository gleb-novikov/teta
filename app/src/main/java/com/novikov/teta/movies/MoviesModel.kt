package com.novikov.teta.movies

class MoviesModel(private val moviesDataSource: MoviesDataSource) {
    fun getMovies() = moviesDataSource.getMovies()
}

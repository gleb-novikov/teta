package com.novikov.teta.movies

interface MoviesDataSource {
    fun getMovies(): List<MovieDto>
}
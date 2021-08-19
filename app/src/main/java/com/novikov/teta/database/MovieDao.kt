package com.novikov.teta.database

import androidx.room.*

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie?>

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getById(id: Int): Movie?

    @Insert
    fun insert(movie: Movie?)

    @Update
    fun update(movie: Movie?)

    @Delete
    fun delete(movie: Movie?)
}

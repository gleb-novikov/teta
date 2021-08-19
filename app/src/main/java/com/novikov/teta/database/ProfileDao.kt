package com.novikov.teta.database

import androidx.room.*

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profiles")
    fun getAll(): List<Profile?>?

    @Query("SELECT * FROM profiles WHERE id = :id")
    fun getById(id: Int): Profile?

    @Insert
    fun insert(profile: Profile?)

    @Update
    fun update(profile: Profile?)

    @Delete
    fun delete(profile: Profile?)
}

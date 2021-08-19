package com.novikov.teta.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.novikov.teta.App

@Database(entities = [Movie::class, Profile::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun profileDao(): ProfileDao

    companion object {
        private const val DATABASE_NAME = "Teta.db"

        val instance: AppDatabase by lazy {
            Room.databaseBuilder(
                App.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        }
    }
}

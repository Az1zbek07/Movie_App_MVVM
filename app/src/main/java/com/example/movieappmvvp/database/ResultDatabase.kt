package com.example.movieappmvvp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [com.example.movieappmvvp.model.Result::class], version = 1, exportSchema = false)
abstract class ResultDatabase: RoomDatabase() {
    abstract val dao: ResultDao

    companion object{
        private var instance: ResultDatabase? = null
        operator fun invoke(context: Context) = instance?: synchronized(Any()){
            instance?: createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context): ResultDatabase{
            return Room.databaseBuilder(
                context,
                ResultDatabase::class.java,
                "Result.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}
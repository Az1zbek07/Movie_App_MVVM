package com.example.movieappmvvp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovie(result: com.example.movieappmvvp.model.Result)

    @Query("SELECT * FROM Result ORDER BY mineId DESC")
    fun getAllLikedMovies(): LiveData<List<com.example.movieappmvvp.model.Result>>
}
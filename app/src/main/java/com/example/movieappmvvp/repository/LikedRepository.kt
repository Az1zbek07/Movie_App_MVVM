package com.example.movieappmvvp.repository

import com.example.movieappmvvp.database.ResultDao

class LikedRepository(
    private val dao: ResultDao
) {
    suspend fun saveMovie(result: com.example.movieappmvvp.model.Result) = dao.saveMovie(result)
    fun getAllSavedMovies() = dao.getAllLikedMovies()
}
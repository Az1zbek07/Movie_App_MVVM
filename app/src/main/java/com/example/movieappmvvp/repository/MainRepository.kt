package com.example.movieappmvvp.repository

import com.example.movieappmvvp.network.ApiService

class MainRepository(
    private val apiService: ApiService
) {
    suspend fun getAllMovies() = apiService.getAllMovies()
    suspend fun searchMovie(text: String) = apiService.searchMovie(query = text)
}
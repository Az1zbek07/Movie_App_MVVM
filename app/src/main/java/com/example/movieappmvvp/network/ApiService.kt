package com.example.movieappmvvp.network

import com.example.movieappmvvp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getAllMovies(
        @Query("api_key") api_key: String = "79c8f4659a3fbb27a6f02497b1812d77"
    ): Response<MovieResponse>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") api_key: String = "79c8f4659a3fbb27a6f02497b1812d77",
        @Query("query") query: String
    ): Response<MovieResponse>
}
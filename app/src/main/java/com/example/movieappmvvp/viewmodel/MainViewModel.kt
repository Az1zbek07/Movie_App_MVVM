package com.example.movieappmvvp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmvvp.database.ResultDatabase
import com.example.movieappmvvp.network.RetrofitClient
import com.example.movieappmvvp.repository.LikedRepository
import com.example.movieappmvvp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val application: Application): ViewModel() {
    private val repository = MainRepository(RetrofitClient.provideRetrofit())
    private val likedRepository = LikedRepository(ResultDatabase.invoke(application).dao)

    fun getAllMovies(): LiveData<List<com.example.movieappmvvp.model.Result>>{
        val movies: MutableLiveData<List<com.example.movieappmvvp.model.Result>> = MutableLiveData()

        try {
            viewModelScope.launch (Dispatchers.IO){
                if (repository.getAllMovies().isSuccessful){
                    movies.postValue(repository.getAllMovies().body()?.results)
                }else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(application, repository.getAllMovies().message(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }catch (e: java.lang.Exception){
            Toast.makeText(application,e.toString(), Toast.LENGTH_SHORT).show()
        }
        return movies
    }



    fun searchMovie(text: String): LiveData<List<com.example.movieappmvvp.model.Result>>{
        val movies: MutableLiveData<List<com.example.movieappmvvp.model.Result>> = MutableLiveData()

        try {
            viewModelScope.launch(Dispatchers.IO) {
                if (repository.searchMovie(text).isSuccessful){
                    movies.postValue(repository.searchMovie(text).body()?.results)
                }else{
                    withContext(Dispatchers.Main){
                        Toast.makeText(application, repository.searchMovie(text).message(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }catch (e: java.lang.Exception){
            Toast.makeText(application, e.toString(), Toast.LENGTH_SHORT).show()
        }

        return movies
    }



    fun saveMovie(result: com.example.movieappmvvp.model.Result){
        viewModelScope.launch(Dispatchers.IO){
            likedRepository.saveMovie(result)
            withContext(Dispatchers.Main){
                Toast.makeText(application, "Saved Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun getAllSavedMovies(): LiveData<List<com.example.movieappmvvp.model.Result>> {
        return likedRepository.getAllSavedMovies()
    }
}
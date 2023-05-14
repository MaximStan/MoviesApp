package com.example.movieapi.module.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.model.response.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val repository: MainRepository): ViewModel() {

   val movies = MutableLiveData<List<Movie>>()

    fun getAllMovieList(){
       viewModelScope.launch {
            withContext(Dispatchers.IO){
                movies.postValue(repository.getMovieList().body()?.results)
            }
       }

    }


}
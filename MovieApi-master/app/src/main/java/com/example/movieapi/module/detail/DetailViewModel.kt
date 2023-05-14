package com.example.movieapi.module.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.model.response.DetailMovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailViewModel(private val repository: DetailRepository) : ViewModel() {
    private var _movie = Channel<DetailMovieResponse?>()
    val movie = _movie.receiveAsFlow()

    fun getMovie(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getMovie(id)
                if (response.isSuccessful){
                    _movie.send(element = response.body())
                }
            }
        }
    }

}
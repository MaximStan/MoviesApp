package com.example.movieapi.module.detail

import com.example.movieapi.network.NetworkService

class DetailRepository(private val service: NetworkService) {
    suspend fun getMovie(id: Long) = service.getMovieItem(id)


}
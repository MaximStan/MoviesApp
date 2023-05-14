package com.example.movieapi.module.main

import com.example.movieapi.network.NetworkService

class MainRepository(val networkService: NetworkService) {

    suspend fun getMovieList() = networkService.getMovieList()

}
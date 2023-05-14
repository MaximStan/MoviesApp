package com.example.movieapi.network

import com.example.movieapi.model.response.DetailMovieResponse
import com.example.movieapi.model.response.ListMovieResponse
import com.example.movieapi.model.response.Movie
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("3/discover/movie")
    suspend fun getMovieList(): Response<ListMovieResponse>

    @GET("3/movie/{movie_id}")
    suspend fun getMovieItem(id: Long): Response<DetailMovieResponse>


}
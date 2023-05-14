package com.example.movieapi.network

import com.example.movieapi.common.BASE_URL
import com.example.movieapi.common.API_MOVIE_KEY
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.Objects


object NetworkServiceHolder {

    private var service: NetworkService

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(object: Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    return chain.proceed(
                        chain.request()
                            .newBuilder()
                            .url(
                                chain.request().url.newBuilder()
                                    .addQueryParameter("api_key", API_MOVIE_KEY).build()
                            )
                            .build()
                    )
                }
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()

        service = retrofit.create(NetworkService::class.java)
    }

   fun getInstance() = service

}
package com.example.movieapi.module.main

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapi.model.response.ListMovieResponse
import com.example.movieapi.model.response.Movie
import com.example.movieapi.network.NetworkService

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    service: NetworkService,
    onClick: (Long) -> Unit
    ) {

    val repository = MainRepository(networkService = service )
    val viewModel = MainViewModel(repository)

    viewModel.getAllMovieList()

    val movies = viewModel.movies.observeAsState()

    if (movies.value != null){

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ){
            items(items = movies.value!!){ item ->
                    MovieCard(movie = item, modifier, onClick)
            }

        }

    }

}


@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier,
    onClick: (Long) -> Unit
    ) {



    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(200.dp)
            .padding(10.dp)
            .clickable { onClick(movie.id) },
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier
               .fillMaxWidth()
               .background(MaterialTheme.colors.primaryVariant)
        ) {

            AsyncImage(
                model = String.format("https://image.tmdb.org/t/p/w500/%s", movie.image),  // full link on movie Image
                contentDescription = "MovieImage",
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))



            )

            Column(Modifier.weight(1f)) {
                Text(
                    text = movie.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    text = movie.overview,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(10.dp),
                    maxLines = 2
                )

            }

        }



    }


}

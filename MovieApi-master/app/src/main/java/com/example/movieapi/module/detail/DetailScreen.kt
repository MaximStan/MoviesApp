package com.example.movieapi.module.detail

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movieapi.network.NetworkService
import com.example.movieapi.ui.theme.Shapes
import com.example.movieapi.ui.theme.Typography

@Composable
fun DetailScreen(
    modifier_prm: Modifier = Modifier,
    service_prm: NetworkService,
    movieId_prm: Long
) {

    val repo = DetailRepository(service = service_prm)
    val viewModel = DetailViewModel(repository = repo)
    val movie = viewModel.movie.collectAsState(initial = null)
    viewModel.getMovie(id = movieId_prm)

    Column(
        modifier = modifier_prm
            .fillMaxSize()
            .padding(bottom = 12.dp)
    ) {
        if (movie.value != null) {
            val info = movie.value!!

            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            ) {
                AsyncImage(
                    model = String.format("https://image.tmdb.org/t/p/w500/%s", info.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop


                )
            }
            Column(Modifier.padding(horizontal = 16.dp)) {
                TextComponent(text_prm = info.title, style_prm = Typography.h1)
                TextComponent(text_prm = info.tagline, style_prm = Typography.h3)
                TextComponent(text_prm = info.overview, style_prm = Typography.body1)
                LazyRowComponent(list = info.listNameLanguages) {
                    Text(text = it.nameLanguage, modifier = Modifier.padding(4.dp))
                }
                LazyRowComponent(list = info.genres) {
                    Text(text = it.name, modifier = Modifier.padding(4.dp))
                }
                TextComponent(text_prm = info.releaseDate)

            }
        }


    }
}

@Composable
fun TextComponent(text_prm: String, style_prm: TextStyle = LocalTextStyle.current) {
    Spacer(modifier = Modifier.size(10.dp))
    Text(text = text_prm, style = style_prm)
}

@Composable
fun <T> LazyRowComponent(list: List<T>, content: @Composable (T) -> Unit) {
    Spacer(modifier = Modifier.size(10.dp))
    LazyRow {
        items(list) {
            Card(shape = Shapes.small) {
                content(it)

            }
            Spacer(modifier = Modifier.size(8.dp))
        }
    }

}


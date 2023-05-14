package com.example.movieapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapi.module.detail.DetailScreen
import com.example.movieapi.module.main.MainScreen
import com.example.movieapi.network.NetworkServiceHolder
import com.example.movieapi.ui.theme.MovieApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApiTheme(true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(topBar = {}) { paddingValues ->
                        val navController = rememberNavController()
                        val service = NetworkServiceHolder.getInstance()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.Main.route ,
                            Modifier.padding(paddingValues)
                        )
                        {
                           composable(Screen.Main.route){
                               MainScreen(
                                   service = service,
                                   onClick = { id ->
                                       navController.navigate(
                                           Screen.Detail
                                               .route
                                               .replace("{id}", id.toString())
                                       )

                                   }
                               )
                           }

                            composable(Screen.Detail.route){
                                DetailScreen(
                                    service_prm = service,
                                    movieId_prm = it.arguments?.getString("id")!!.toLong()
                                )
                            }

                        }


                    }

                }
            }
        }
    }
}

sealed class Screen(val route: String){

    object Main: Screen("main")

    object Detail: Screen("detail/{id}")


}
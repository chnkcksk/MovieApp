package com.chnkcksk.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chnkcksk.movieapp.presentation.movie_detail.MovieDetailViewModel
import com.chnkcksk.movieapp.presentation.movie_detail.views.MovieDetailScreen
import com.chnkcksk.movieapp.presentation.movies.MoviesViewModel
import com.chnkcksk.movieapp.presentation.movies.views.MoviesScreen
import com.chnkcksk.movieapp.presentation.ui.theme.MovieAppTheme
import com.chnkcksk.movieapp.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.MoviesScreen.route
                    ) {
                        composable(route = Screen.MoviesScreen.route) {
                            MoviesScreen(
                                navController = navController,
                            )
                        }

                        composable(route = Screen.MovieDetailScreen.route + "/{${IMDB_ID}}") {
                            MovieDetailScreen()
                        }

                    }

                }
            }
        }
    }
}

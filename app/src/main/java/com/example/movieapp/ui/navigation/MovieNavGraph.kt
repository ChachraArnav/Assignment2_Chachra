package com.example.movieapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.ui.screens.MovieDetailScreen
import com.example.movieapp.ui.screens.MovieSearchScreen
import com.example.movieapp.viewmodel.MovieViewModel
import androidx.hilt.navigation.compose.hiltViewModel

sealed class Screen(val route: String) {
    object Search : Screen("movie_search")
    object Detail : Screen("movie_detail/{movieId}") {
        fun createRoute(movieId: String) = "movie_detail/$movieId"
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MovieNavGraph(navController: NavHostController, viewModel: MovieViewModel) {
    NavHost(navController = navController, startDestination = Screen.Search.route) {
        composable(Screen.Search.route) {
            MovieSearchScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.Detail.route) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            val selectedMovie = viewModel.movies.value.find { it.imdbId == movieId }

            selectedMovie?.let {
                MovieDetailScreen(movie = it, navController = navController)
            }
        }
    }
}

package com.example.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.data.repo.MovieRepository
import com.example.movieapp.data.network.Api
import com.example.movieapp.ui.navigation.MovieNavGraph
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.MovieViewModel
import com.example.movieapp.viewmodel.MovieViewModelFactory

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()

                // Fix: Use MovieViewModelFactory to create ViewModel
                val movieRepository = remember { MovieRepository(Api.create()) }
                val factory = remember { MovieViewModelFactory(movieRepository) }
                val movieViewModel: MovieViewModel = viewModel(factory = factory)

                Scaffold {
                    MovieNavGraph(navController = navController, viewModel = movieViewModel)
                }
            }
        }
    }
}

package com.example.movieapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.movieapp.data.model.Movie
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController  // Ensure this import is present

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(movie: Movie, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = movie.title) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberImagePainter(movie.posterUrl),
                contentDescription = movie.title,
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Title: ${movie.title}")
            Text(text = "Year: ${movie.year}")
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Back")

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(
        movie = Movie(
            title = "Inception",
            year = "2010",
            posterUrl = "https://image.tmdb.org/t/p/w500/qmDpIHrmpJINaRKAfWQfftjCdyi.jpg",
            imdbId = "tt1375666"
        ),
        navController = rememberNavController()  // This will now work after importing the correct package
    )
}

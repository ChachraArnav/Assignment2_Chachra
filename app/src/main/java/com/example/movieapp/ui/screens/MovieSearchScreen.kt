package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieapp.viewmodel.MovieViewModel

@Composable
fun MovieSearchScreen(navController: NavController, viewModel: MovieViewModel) {
    var query by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Collecting the search state from the ViewModel
    val searchState = viewModel.searchState.collectAsState().value
    isLoading = searchState.isLoading
    errorMessage = searchState.error

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Search Movies") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.searchMovies(query) },
            modifier = Modifier.fillMaxWidth(),
            enabled = query.isNotEmpty() && !isLoading
        ) {
            Text("Search")
        }

        if (isLoading) {
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieSearchScreenPreview() {
    // You may need to use the hiltViewModel function for ViewModel injection
    val viewModel: MovieViewModel = hiltViewModel()  // Ensure hiltViewModel is correctly imported
    MovieSearchScreen(navController = rememberNavController(), viewModel = viewModel)
}

package com.example.movieapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.repo.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.asStateFlow

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    // State for movie list
    private val _movies = mutableStateOf<List<Movie>>(emptyList())
    val movies: State<List<Movie>> = _movies

    // State for selected movie details
    private val _movieDetails = mutableStateOf<Movie?>(null)
    val movieDetails: State<Movie?> = _movieDetails

    // State for search/loading status
    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    // Function to search movies
    fun searchMovies(query: String) {
        _searchState.value = _searchState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                // Fetch movie search results from the repository
                val response = movieRepository.searchMovies(query)

                // Ensure that the response is a successful list of movies
                if (response != null && response.isNotEmpty()) {
                    _movies.value = response  // Set the list of movies
                } else {
                    _movies.value = emptyList()  // Handle no results
                }

                _searchState.value = _searchState.value.copy(isLoading = false)
            } catch (e: Exception) {
                _searchState.value = _searchState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    // Function to get movie details based on imdbID
    fun getMovieDetails(imdbID: String) {
        _searchState.value = _searchState.value.copy(isLoading = true)
        viewModelScope.launch {
            try {
                // Fetch movie details from the repository
                val response = movieRepository.getMovieDetails(imdbID)
                _movieDetails.value = response // Set the movie details state
                _searchState.value = _searchState.value.copy(isLoading = false)
            } catch (e: Exception) {
                _searchState.value = _searchState.value.copy(isLoading = false, error = e.message)
            }
        }
    }

    data class SearchState(
        val isLoading: Boolean = false,
        val error: String? = null
    )
}

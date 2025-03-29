package com.example.movieapp.data.repo

import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.network.Api

class MovieRepository(private val apiService: Api) {

    suspend fun searchMovies(query: String): List<Movie> {
        // Assuming the API returns a MovieResponse object, which contains the Search list.
        val response = apiService.searchMovies(query)
        return response?.Search ?: emptyList()  // Return the list of movies, or an empty list if no movies found
    }

    suspend fun getMovieDetails(imdbID: String): Movie {
        // Directly fetch and return movie details from the API
        return apiService.getMovieDetails(imdbID)
    }
}

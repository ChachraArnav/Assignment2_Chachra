package com.example.movieapp.data.network

import com.example.movieapp.data.model.Movie

data class MovieResponse(
    val Search: List<Movie>?,
    val totalResults: String?,
    val Response: String
)

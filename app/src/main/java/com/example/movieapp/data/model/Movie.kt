package com.example.movieapp.data.model

data class Movie(
    val imdbId: String,
    val title: String,
    val year: String,
    val posterUrl: String  // Fixed: Renamed 'poster' to 'posterUrl' for consistency
)

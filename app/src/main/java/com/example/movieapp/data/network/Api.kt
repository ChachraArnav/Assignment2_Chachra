package com.example.movieapp.data.network

import com.example.movieapp.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface Api {
    @GET("?apikey=b5ce9477&type=movie")
    suspend fun searchMovies(@Query("s") query: String): MovieResponse

    // Add method for fetching movie details using imdbID
    @GET("?apikey=b5ce9477")
    suspend fun getMovieDetails(@Query("i") imdbID: String): Movie

    companion object {
        private const val BASE_URL = "https://www.omdbapi.com/"

        // Create Retrofit instance
        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}

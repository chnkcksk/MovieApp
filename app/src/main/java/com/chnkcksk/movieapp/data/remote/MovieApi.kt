package com.chnkcksk.movieapp.data.remote

import com.chnkcksk.movieapp.data.remote.dto.MovieDetailDto
import com.chnkcksk.movieapp.data.remote.dto.MovieDto
import com.chnkcksk.movieapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    //https://www.omdbapi.com/?apikey=b04fd10&s=batman
    //https://www.omdbapi.com/?apikey=b04fd10&i=tt3896198

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apikey: String = API_KEY
    ) : MovieDto

    @GET(".")
    suspend fun getMovieDetails(
        @Query("i") imdbId: String,
        @Query("apikey") apikey: String = API_KEY
    ) : MovieDetailDto

}
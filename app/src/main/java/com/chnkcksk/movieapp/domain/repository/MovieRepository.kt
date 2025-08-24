package com.chnkcksk.movieapp.domain.repository

import com.chnkcksk.movieapp.data.remote.dto.MovieDetailDto
import com.chnkcksk.movieapp.data.remote.dto.MovieDto

interface MovieRepository {

    suspend fun getMovies(search: String): MovieDto

    suspend fun getMovieDetail(imdbId: String): MovieDetailDto

}
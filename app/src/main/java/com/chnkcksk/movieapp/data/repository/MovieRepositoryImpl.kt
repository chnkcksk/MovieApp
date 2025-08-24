package com.chnkcksk.movieapp.data.repository

import com.chnkcksk.movieapp.data.remote.MovieApi
import com.chnkcksk.movieapp.data.remote.dto.MovieDetailDto
import com.chnkcksk.movieapp.data.remote.dto.MovieDto
import com.chnkcksk.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: MovieApi) : MovieRepository {

    override suspend fun getMovies(search: String): MovieDto {
        return api.getMovies(searchString = search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetails(imdbId = imdbId)
    }

}
package com.chnkcksk.movieapp.domain.use_case.get_movies

import coil.network.HttpException
import com.chnkcksk.movieapp.data.remote.dto.Search
import com.chnkcksk.movieapp.data.remote.dto.toMovieList
import com.chnkcksk.movieapp.domain.model.Movie
import com.chnkcksk.movieapp.domain.repository.MovieRepository
import com.chnkcksk.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    fun executeGetMovies(search: String): Flow<Resource<List<Movie>>> = flow {

        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(search)

            if (movieList.Response.equals("True")) {
                emit(Resource.Success(movieList.toMovieList()))
            } else {
                emit(Resource.Error(message = "No movie found!"))
            }

        } catch (e: IOError) {
            emit(Resource.Error(message = "No internet connection!"))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }

    }


}
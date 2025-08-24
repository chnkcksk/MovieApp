package com.chnkcksk.movieapp.domain.use_case.get_movie_detail

import retrofit2.HttpException
import com.chnkcksk.movieapp.data.remote.dto.toMovieDetail
import com.chnkcksk.movieapp.domain.model.MovieDetail
import com.chnkcksk.movieapp.domain.repository.MovieRepository
import com.chnkcksk.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import android.util.Log
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    fun executeGetMovieDetails(imdbId: String): Flow<Resource<MovieDetail>> = flow {

        try {
            Log.d("GetMovieDetailsUseCase", "Fetching movie details for IMDB ID: $imdbId")
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId = imdbId).toMovieDetail()
            Log.d("GetMovieDetailsUseCase", "Successfully fetched movie details: ${movieDetail.Title}")
            emit(Resource.Success(movieDetail))

        } catch (e: IOException) {
            Log.e("GetMovieDetailsUseCase", "IO Error: ${e.localizedMessage}")
            emit(Resource.Error(message = "No internet connection!"))
        } catch (e: HttpException) {
            Log.e("GetMovieDetailsUseCase", "HTTP Error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        } catch (e: Exception) {
            Log.e("GetMovieDetailsUseCase", "Generic Error: ${e.localizedMessage}")
            emit(Resource.Error(message = e.localizedMessage ?: "Unexpected error occurred"))
        }

    }

}
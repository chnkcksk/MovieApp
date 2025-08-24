package com.chnkcksk.movieapp.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.chnkcksk.movieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.chnkcksk.movieapp.presentation.movies.MoviesState
import com.chnkcksk.movieapp.util.Constants.IMDB_ID
import com.chnkcksk.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<MovieDetailState>(MovieDetailState()) //dosya icinde bunu
    val state: State<MovieDetailState> = _state  // view icinde bunu

    init {
        val imdbId = savedStateHandle.get<String>(IMDB_ID)
        Log.d("MovieDetailViewModel", "IMDB ID from savedStateHandle: $imdbId")
        imdbId?.let {
            getMovieDetail(it)
        } ?: run {
            Log.e("MovieDetailViewModel", "IMDB ID is null!")
        }
    }

    private fun getMovieDetail(imdbId: String) {
        Log.d("MovieDetailViewModel", "Getting movie detail for IMDB ID: $imdbId")
        getMovieDetailsUseCase.executeGetMovieDetails(imdbId = imdbId).onEach {
            when (it) {
                is Resource.Error -> {
                    Log.e("MovieDetailViewModel", "Error getting movie details: ${it.message}")
                    _state.value = MovieDetailState(error = it.message ?: "Error")
                }

                is Resource.Loading -> {
                    Log.d("MovieDetailViewModel", "Loading movie details...")
                    _state.value = MovieDetailState(isLoading = true)
                }

                is Resource.Success -> {
                    Log.d("MovieDetailViewModel", "Successfully loaded movie details: ${it.data?.Title}")
                    _state.value = MovieDetailState(movie = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

}
package com.chnkcksk.movieapp.presentation.movies

import com.chnkcksk.movieapp.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val search: String = "batman"
)

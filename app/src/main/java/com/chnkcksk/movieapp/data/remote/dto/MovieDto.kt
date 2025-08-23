package com.chnkcksk.movieapp.data.remote.dto

import com.chnkcksk.movieapp.domain.model.Movie

data class MovieDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

fun MovieDto.toMovieList(): List<Movie> {

    return Search.map { search -> Movie(
        Poster = search.Poster,
        Title = search.Title,
        Year = search.Year,
        imdbID = search.imdbID
    ) }

}
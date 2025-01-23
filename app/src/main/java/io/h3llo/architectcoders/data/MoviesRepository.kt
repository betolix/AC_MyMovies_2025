package io.h3llo.architectcoders.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MoviesRepository {

    suspend fun fetchPopularMovies(): List<Movie> =
        MoviesClient
            .instance
            .fetchPopularMovies("US")
            .results
            .map { it.toDomainModel() }

}


private fun RemoteMovie.toDomainModel(): Movie =
    Movie(
        id = id,
        title = title,
        poster = "https://image.tmdb.org/t/p/w185/$posterPath",
    )

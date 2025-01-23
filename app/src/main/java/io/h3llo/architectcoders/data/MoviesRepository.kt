package io.h3llo.architectcoders.data

class MoviesRepository {

    suspend fun fetchPopularMovies(region: String): List<Movie> =
        MoviesClient
            .instance
            .fetchPopularMovies("US")
            .results
            .map { it.toDomainModel() }

    suspend fun fetchMovieById(id: Int): Movie =
        MoviesClient
            .instance
            .fetchMovieById(id)
            .toDomainModel()

}


private fun RemoteMovie.toDomainModel(): Movie =
    Movie(
        id = id,
        title = title,
        poster = "https://image.tmdb.org/t/p/w185/$posterPath",
    )

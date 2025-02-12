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


private fun RemoteMovie.toDomainModel() = Movie(
    id,
    title,
    overview,
    releaseDate,
    "https://image.tmdb.org/t/p/w185/$posterPath",
    backdropPath?.let { "https://image.tmdb.org/t/p/w780/$it" },
    originalTitle,
    originalLanguage,
    popularity,
    voteAverage
)














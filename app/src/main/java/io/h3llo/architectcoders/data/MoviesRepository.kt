package io.h3llo.architectcoders.data

class MoviesRepository(
    private val regionRepository: RegionRepository
) {
    suspend fun fetchPopularMovies(): List<Movie> =
        MoviesClient
            .instance
            .fetchPopularMovies(regionRepository.findLastRegion())
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














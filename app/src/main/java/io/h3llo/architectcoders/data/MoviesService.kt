package io.h3llo.architectcoders.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie?sort_by_popularity.desc?")
    suspend fun fetchPopularMovies(@Query("region") region : String) : RemoteResult

    @GET("movie/{id}")
    suspend fun fetchMovieById(@Path("id") id: Int) : RemoteMovie

}
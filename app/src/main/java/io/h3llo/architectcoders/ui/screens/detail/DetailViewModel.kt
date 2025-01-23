package io.h3llo.architectcoders.ui.screens.detail


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.h3llo.architectcoders.data.Movie
import io.h3llo.architectcoders.data.MoviesRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int): ViewModel() {

    private val repository : MoviesRepository = MoviesRepository()

    var state by mutableStateOf(UiState())
        private set

    init{
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, movie = repository.fetchMovieById(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? =  null
    )



}

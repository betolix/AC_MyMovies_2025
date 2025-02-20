package io.h3llo.architectcoders.ui.screens.detail


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.h3llo.architectcoders.data.Movie
import io.h3llo.architectcoders.data.MoviesRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MoviesRepository,
    private val id: Int
) : ViewModel() {

    // var state by mutableStateOf(UiState())
    //    private set
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? =  null,
        val message: String? = null
    )



    init{
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, movie = repository.fetchMovieById(id))
        }
    }

    fun onFavoriteClicked() {
        //_events.trySend(UiEvent.ShowMessage("Favorite clicked"))
        _state.update { it.copy(message = "Favorite clicked") }
    }

    fun onMessageShown() {
        _state.update { it.copy(message = null) }
    }


}

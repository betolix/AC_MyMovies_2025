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
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int): ViewModel() {

    private val repository : MoviesRepository = MoviesRepository()

    // var state by mutableStateOf(UiState())
    //    private set
    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    data class UiState(
        val loading: Boolean = false,
        val movie: Movie? =  null
    )

    sealed interface UiEvent {
        data class ShowMessage(val message: String) : UiEvent
    }

    private val _events = Channel<UiEvent>()
    val events = _events.receiveAsFlow()

    init{
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, movie = repository.fetchMovieById(id))
        }
    }

    fun onFavoriteClicked() {
        _events.trySend(UiEvent.ShowMessage("Favorite clicked"))
    }





}

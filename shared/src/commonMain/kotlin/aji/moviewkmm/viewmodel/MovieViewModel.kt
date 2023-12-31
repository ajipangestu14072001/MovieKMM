package aji.moviewkmm.viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import aji.moviewkmm.domain.async.AsyncResult
import aji.moviewkmm.domain.interactor.MovieInteractor
import aji.moviewkmm.domain.model.Movie

class MovieViewModel constructor(private val interactor: MovieInteractor) : ViewModel() {

    private val moviesMutable =
        MutableStateFlow<AsyncResult>(AsyncResult.Uninitialized)
    val movies = moviesMutable.asStateFlow()

    private val selectedMovieMutable = MutableStateFlow(Movie())
    val selectedMovie = selectedMovieMutable.asStateFlow()

    init {
        loadMovies()
    }

    fun loadMovies() {
        moviesMutable.value = AsyncResult.Loading
        viewModelScope.launch {
            try {
                val movie = interactor.getMovies()
                moviesMutable.value = AsyncResult.Success(movie)
            } catch (e: Exception) {
                e.printStackTrace()
                moviesMutable.value = AsyncResult.Error(e.message.orEmpty())
            }
        }
    }

    fun selectMovie(movie: Movie) {
        selectedMovieMutable.value = movie
    }


    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}
package aji.moviewkmm.domain.interactor

import aji.moviewkmm.domain.model.Movie

interface MovieInteractor {

    suspend fun getMovies(): List<Movie>

}
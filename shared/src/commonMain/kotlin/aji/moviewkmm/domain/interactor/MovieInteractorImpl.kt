package aji.moviewkmm.domain.interactor

import aji.moviewkmm.data.MovieRepository
import aji.moviewkmm.domain.model.Movie

class MovieInteractorImpl(private val movieRepository: MovieRepository) : MovieInteractor {
    override suspend fun getMovies(): List<Movie> {
        return movieRepository.getMovies()
    }

}
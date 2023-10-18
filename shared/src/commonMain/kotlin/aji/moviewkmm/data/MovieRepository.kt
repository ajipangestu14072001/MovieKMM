package aji.moviewkmm.data

import aji.moviewkmm.data.mapper.MovieDataMapper
import aji.moviewkmm.data.source.MovieRemoteDataSource
import aji.moviewkmm.domain.model.Movie

class MovieRepository(
    private val remoteDataSource: MovieRemoteDataSource,
    private val dataMapper: MovieDataMapper
) {

    suspend fun getMovies() : List<Movie> = remoteDataSource.getMovies().data.map {
        dataMapper.mapMovieResponse(it)
    }

}
package aji.moviewkmm.data.mapper

import aji.moviewkmm.data.constant.NetworkConstant
import aji.moviewkmm.data.model.MovieResponse
import aji.moviewkmm.domain.model.Movie

class MovieDataMapper {

    fun mapMovieResponse(response: MovieResponse) = Movie(
        id = response.id,
        title = response.title.orEmpty(),
        description = response.overview.orEmpty(),
        posterUrl = buildString {
            append(NetworkConstant.POSTER_BASE_URL)
            append(response.posterPath.orEmpty())
        },
        bannerUrl = buildString {
            append(NetworkConstant.BACKDROP_BASE_URL)
            append(response.backdropPath.orEmpty())
        },
        rating = response.voteAverage,
        releaseDate = response.releaseDate.orEmpty()
    )



}
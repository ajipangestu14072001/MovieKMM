package aji.moviewkmm.data.source

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import aji.moviewkmm.data.constant.NetworkConstant.API_KEY
import aji.moviewkmm.data.constant.NetworkConstant.BASE_URL
import aji.moviewkmm.data.model.BaseResponse
import aji.moviewkmm.data.model.MovieResponse

class MovieRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun getMovies(): BaseResponse<List<MovieResponse>> {
        return httpClient.get("${BASE_URL}movie/now_playing") {
            parameter("api_key", API_KEY)
        }.body()
    }
}
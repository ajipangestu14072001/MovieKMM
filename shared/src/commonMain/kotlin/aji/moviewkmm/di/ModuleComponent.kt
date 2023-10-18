package aji.moviewkmm.di


import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import aji.moviewkmm.data.MovieRepository
import aji.moviewkmm.data.mapper.MovieDataMapper
import aji.moviewkmm.data.source.MovieRemoteDataSource
import aji.moviewkmm.domain.interactor.MovieInteractor
import aji.moviewkmm.domain.interactor.MovieInteractorImpl
import aji.moviewkmm.platformModule

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    modules(
        appModule(),
        platformModule()
    )
}

fun initKoin() = initKoin {

}

fun appModule() = module {
    single {
        MovieDataMapper()
    }
    single {
        MovieRemoteDataSource(get())
    }

    single {
        MovieRepository(get(), get())
    }

    single<MovieInteractor> {
        MovieInteractorImpl(get())
    }


    single {
        Json { isLenient = true; ignoreUnknownKeys = true }
    }


    single {
        HttpClient(get()) {
            install(ContentNegotiation) {
                json(get())
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }
}


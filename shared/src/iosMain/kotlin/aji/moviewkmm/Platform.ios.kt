package aji.moviewkmm

import aji.moviewkmm.viewmodel.MovieViewModel
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun platformModule() = module {
    single {
        Darwin.create()
    }

    factory {
        MovieViewModel(get())
    }
}

object ViewModelProvider : KoinComponent {
    fun getMovieViewModel() = get<MovieViewModel>()

}
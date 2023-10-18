package aji.moviewkmm.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import aji.moviewkmm.di.initKoin

class KMMMovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@KMMMovieApp)
        }
    }
}
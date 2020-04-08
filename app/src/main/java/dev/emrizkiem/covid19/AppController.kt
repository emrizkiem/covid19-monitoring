package dev.emrizkiem.covid19

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dev.emrizkiem.covid19.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppController: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger()
            androidContext(this@AppController)
            Modules()
        }
    }

    companion object {
        private var instance: AppController? = null
        val context: Context?
        get() = instance
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}
package br.com.livroandroid.carros

import android.app.Application
import android.util.Log

import com.squareup.otto.Bus

class CarrosApplication : Application() {
    val bus = Bus()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "CarrosApplication.onCreate()")
        // Salva a instância para termos acesso como Singleton
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.d(TAG, "CarrosApplication.onTerminate()")
    }

    companion object {

        private val TAG = "CarrosApplication"
        // Singleton
        var instance: CarrosApplication? = null
            private set
    }

}

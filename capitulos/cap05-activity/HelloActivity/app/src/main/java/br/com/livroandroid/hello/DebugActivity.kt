package br.com.livroandroid.hello

/**
 * Created by Ricardo Lecheta on 17/12/2014.
 */

import android.app.Activity
import android.os.Bundle
import android.util.Log

/* Activity que imprime logs nos métodos de ciclo de vida */
open class DebugActivity : AppCompat() {
    private val TAG = "livro"

    // Nome da classe sem o pacote
    private val className: String
        get() {
            val s = javaClass.name
            return s.substring(s.lastIndexOf("."))
        }

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        Log.d(TAG, className + ".onCreate() chamado: " + icicle)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, className + ".onStart() chamado.")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, className + ".onRestart() chamado.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, className + ".onResume() chamado.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, className + ".onPause() chamado.")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, className + ".onSaveInstanceState() chamado.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, className + ".onStop() chamado.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, className + ".onDestroy() chamado.")
    }
}


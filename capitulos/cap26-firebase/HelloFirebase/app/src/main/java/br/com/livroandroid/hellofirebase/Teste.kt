package br.com.livroandroid.hellofirebase

import android.os.Bundle

object Teste {
    fun toBundle(map: Map<String, String>): Bundle {
        val bundle = Bundle()
        for (key in map.keys) {
            bundle.putString(key, map.get(key))
        }
        return bundle
    }
}

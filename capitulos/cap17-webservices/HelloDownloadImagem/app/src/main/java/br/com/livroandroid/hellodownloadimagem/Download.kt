package br.com.livroandroid.hellodownloadimagem

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log

import java.net.URL

object Download {
    fun downloadBitmap(url: String): Bitmap? {
        try {
            Thread.sleep(500)
            URL(url).openStream().use {
                val bitmap = BitmapFactory.decodeStream(it)
                return bitmap
            }
        }catch (exception: Exception) {
            Log.e("livro",exception.message,exception)
            return null
        }
    }
}

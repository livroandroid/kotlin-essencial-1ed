package br.com.livroandroid.hellodownloadimagem

import android.graphics.Bitmap
import android.graphics.BitmapFactory

import java.net.URL

object Download {
    fun downloadBitmap(url: String): Bitmap {
        Thread.sleep(1500)
        URL(url).openStream().use {
            val bitmap = BitmapFactory.decodeStream(it)
            return bitmap
        }
    }
}

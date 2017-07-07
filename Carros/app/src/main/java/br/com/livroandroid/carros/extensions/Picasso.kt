package br.com.livroandroid.carros.extensions

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?, progress: ProgressBar? = null) {
    if (url == null || url.trim().isEmpty()) {
        setImageBitmap(null)
        return
    }
    if (progress == null) {
        Picasso.with(context).load(url).fit().into(this)
    } else {
        progress.visibility = View.VISIBLE
        Picasso.with(context).load(url).fit().into(this,
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        // Download OK
                        progress.visibility = View.GONE
                    }

                    override fun onError() {
                        progress.visibility = View.GONE
                    }
                })
    }
}

package br.com.livroandroid.extensions

import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity

// Para facilitar o setOnClickListener
fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick: (v: android.view.View?) -> Unit) {
    val view = findViewById(viewId)
    view.setOnClickListener { onClick(it) }
}
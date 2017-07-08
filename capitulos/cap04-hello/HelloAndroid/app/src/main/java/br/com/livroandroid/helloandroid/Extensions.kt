package br.com.livroandroid.helloandroid

import android.app.Activity
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast

// Mostra um toast
fun Activity.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, length).show()

// Busca um TextView e retorna seu texto
fun Activity.getTextString(@IdRes id: Int): String {
    val textView = findViewById<TextView>(id)
    val s = textView.text.toString()
    return s
}

// Evento de clique
fun AppCompatActivity.onClick(@IdRes viewId: Int, onClick: (v: android.view.View?) -> Unit) {
    val view = findViewById<View>(viewId)
    view.setOnClickListener { onClick(it) }
}

package br.com.livroandroid.helloandroid

import android.app.Activity
import android.support.annotation.IdRes
import android.widget.TextView
import android.widget.Toast

// Mostra um toast
fun Activity.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, length).show()

// Busca um TextView e retorna seu texto
fun Activity.getText(@IdRes id: Int): String {
    val textView = findViewById<TextView>(id)
    val s = textView.text.toString()
    return s
}

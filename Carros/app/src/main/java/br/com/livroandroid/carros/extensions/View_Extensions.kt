package br.com.livroandroid.carros.extensions

// Utilizar onClick ao invés de setOnClickListener
fun android.view.View.onClick(l: (v: android.view.View?) -> Unit) {
    setOnClickListener(l)
}
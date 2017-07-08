package br.com.livroandroid.helloandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        // View
        setContentView(R.layout.activity_main)
        onClick(R.id.btLogin) { onClickLogin() }
    }

    fun onClickLogin() {
        // Lê os textos digitados com a extension
        val login = getTextString(R.id.tLogin)
        val senha = getTextString(R.id.tSenha)
        if ("ricardo" == login && "123" == senha) {
            toast("Bem-vindo, login realizado com sucesso.")
        } else {
            toast("Login e senha incorretos.")
        }
    }
}

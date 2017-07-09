package br.com.livroandroid.hellofirebase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    val TAG = "firebase"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().subscribeToTopic("news")

        // Se precisar imprimir o Token do Firebase
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG,"Token: " + token)

        // Ao clicar na notificação os parâmetros são enviados pela Intent
        Log.d(TAG, "Nome: " + intent.getStringExtra("nome"));
        Log.d(TAG, "Sobrenome: " + intent.getStringExtra("sobrenome"));
    }
}
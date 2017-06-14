package br.com.livroandroid.helloreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.livroandroid.hellonotification.NotificationUtil

class HelloReceiver : BroadcastReceiver() {
    override fun onReceive(c: Context, it: Intent) {
        Log.d("livroandroid", "Hello Receiver !!!")

        // Cria uma notificação.
        val intent = Intent(c, MainActivity::class.java)
        NotificationUtil.create(c, 1, intent, "Livro Android", "Hello Receiver")
    }
}

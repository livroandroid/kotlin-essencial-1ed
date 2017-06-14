package br.com.livroandroid.hellofirebase

import android.content.Intent
import android.util.Log
import br.com.livroandroid.extensions.toBundle
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "firebase"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "onMessageReceived")

        // Verifica se a mensagem é do tipo dados (data messages)
        if (remoteMessage.data.isNotEmpty()) {
            val data = remoteMessage.data
            Log.d(TAG, "Dados: " + data)
            Log.d(TAG, "Nome: " + data.get("nome"))
			Log.d(TAG, "Sobrenome: " + data.get("sobrenome"))
        }

        // Verifica se a mensagem é do tipo notificação.
        if (remoteMessage.notification != null) {
            val title = remoteMessage.notification.title
            val msg = remoteMessage.notification.body!!
            Log.d(TAG, "Message Notification Title: " + title)
            Log.d(TAG, "Message Notification Body: " + msg)

            showNotification(remoteMessage);
        }
    }

    // Mostra uma notificação
    private fun showNotification(remoteMessage: RemoteMessage) {
        // Intent para abrir a MainActivity
        val intent = Intent(this, MainActivity::class.java)
        // Adiciona os parâmetros na intent
        intent.putExtras(remoteMessage.data.toBundle())

        // Title: O título é opcional...
        val title = remoteMessage.notification.title ?: getString(R.string.app_name)

        // Mensagem
        val msg = remoteMessage.notification.body!!

        // Cria uma notificação.
        NotificationUtil.create(this, 1, intent, title, msg)
    }
}

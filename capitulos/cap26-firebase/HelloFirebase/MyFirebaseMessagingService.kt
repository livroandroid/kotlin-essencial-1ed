package br.com.livroandroid.hellofirebase

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    val TAG = "firebase"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "onMessageReceived")

        // Verifica se a mensagem é do tipo notificação.
        if (remoteMessage.notification != null) {
            val title = remoteMessage.notification.title
            val msg = remoteMessage.notification.body!!
            Log.d(TAG, "Message Notification Title: " + title)
            Log.d(TAG, "Message Notification Body: " + msg)
        }
    }
}

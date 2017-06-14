package br.com.livroandroid.helloalarme

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.livroandroid.hellonotification.NotificationUtil
import java.util.*

class LembremeDeComerReceiver : BroadcastReceiver() {

    companion object {
        // Constantes
        val TAG = "livroandroid"
        val ACTION = "br.com.livroandroid.helloalarme.LEMBREME_DE_COMER"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Você precisa comer: " + Date())

        val notifIntent = Intent(context, MainActivity::class.java)

        NotificationUtil.create(context, 1, notifIntent,
                "Hora de comer algo...",
                "Que tal uma fruta?")
    }
}

package br.com.livroandroid.helloalarme

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import br.com.livroandroid.hellonotification.NotificationUtil
import java.util.*

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("livro", "BootReceiver!")
    }
}

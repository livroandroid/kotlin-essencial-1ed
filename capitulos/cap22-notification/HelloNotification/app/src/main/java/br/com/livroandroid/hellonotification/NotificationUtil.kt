package br.com.livroandroid.hellonotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat

object NotificationUtil {

    internal val CHANNEL_ID = "1"

    // Registra o canal (channel)
    fun createChannel(context: Context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val appName = context.getString(R.string.app_name)
            val c = NotificationChannel(CHANNEL_ID, appName, NotificationManager.IMPORTANCE_DEFAULT)
            c.lightColor = Color.BLUE
            c.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            manager.createNotificationChannel(c)
        }
    }

    // Cria uma notificação
    fun create(context: Context, id: Int, intent: Intent, title: String, msg: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Intent para disparar o broadcast
        val p = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        // Cria a notificação
        val builder = NotificationCompat.Builder(context, "id")
                .setContentIntent(p)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
        // Dispara a notificação
        val n = builder.build()
        manager.notify(id, n)
    }
}

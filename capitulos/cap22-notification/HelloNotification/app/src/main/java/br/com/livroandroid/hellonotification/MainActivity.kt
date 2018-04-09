package br.com.livroandroid.hellonotification

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_main)
        // Cria o canal (channel) para a notificação
        NotificationUtil.createChannel(this)

        findViewById<Button>(R.id.btNotificacao).setOnClickListener {
            // Cria a intent que será chamada ao clicar na notificação
            // Neste caso vamos abrir a MainActivity novamente
            val intent = Intent(this, MainActivity::class.java)
            NotificationUtil.create(this, 1, intent, "Livro Android", "Hello Notification")
        }
    }
}

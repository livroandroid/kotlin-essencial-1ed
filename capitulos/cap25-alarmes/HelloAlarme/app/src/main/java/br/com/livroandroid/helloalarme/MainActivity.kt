package br.com.livroandroid.helloalarme

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.livroandroid.extensions.onClick
import br.com.livroandroid.hellonotification.NotificationUtil
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_main)
        // Cria o canal (channel) para a notificação
        NotificationUtil.createChannel(this)
        // Trata os eventos
        onClick(R.id.btAgendar) { agendar() }
        onClick(R.id.btAgendarComRepeat) { agendarComRepeat() }
        onClick(R.id.btCancelar) { cancelar() }
    }

    fun agendar() {
        val intent = Intent(this, LembremeDeComerReceiver::class.java)
        // Agenda para daqui a 5 seg
        AlarmUtil.schedule(this, intent, getTime())
        Toast.makeText(this, "Alarme agendado.", Toast.LENGTH_SHORT).show()
    }

    fun agendarComRepeat() {
        val intent = Intent(this, LembremeDeComerReceiver::class.java)
        // Agenda para daqui a 5 seg, repete a cada 30 seg
        AlarmUtil.scheduleRepeat(this, intent, getTime(), (30 * 1000).toLong())
        Toast.makeText(this, "Alarme agendado com repetir.", Toast.LENGTH_SHORT).show()
    }

    fun cancelar() {
        val intent = Intent(this, LembremeDeComerReceiver::class.java)
        AlarmUtil.cancel(this, intent)
        Toast.makeText(this, "Alarme cancelado", Toast.LENGTH_SHORT).show()
    }

    // Data/Tempo para agendar o alarme
    fun getTime(): Long {
        val c = Calendar.getInstance()
        c.timeInMillis = System.currentTimeMillis()
        c.add(Calendar.SECOND, 5)
        return c.timeInMillis
    }
}


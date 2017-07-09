package br.com.livroandroid.helloreceiver

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btEnviarBroadcast).setOnClickListener {
            sendBroadcast(Intent("BINGO"))
            Toast.makeText(this, "Intent enviada!", Toast.LENGTH_SHORT).show()
        }
    }
}

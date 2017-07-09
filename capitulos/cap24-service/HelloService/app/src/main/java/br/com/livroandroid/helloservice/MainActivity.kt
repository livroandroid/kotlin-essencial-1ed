package br.com.livroandroid.helloservice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start
        findViewById<Button>(R.id.btStart).setOnClickListener {
            startService(Intent(this, HelloService::class.java))
        }
        // Stop
        findViewById<Button>(R.id.btStop).setOnClickListener {
            stopService(Intent(this, HelloService::class.java))
        }
    }
}


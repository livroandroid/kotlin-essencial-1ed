package br.com.livroandroid.hellodownloadimagem

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.app.ProgressDialog
import android.widget.Toast
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val img = findViewById(R.id.img) as ImageView

        val dialog = ProgressDialog.show(this, "Download",
			"Fazendo o download da imagem, por favor aguarde...", false, true)

        Thread {
            val URL = "http://static.novatec.com.br/capas-ampliadas/capa-ampliada-9788575224687.jpg"

            // Download da imagem
            val bitmap = Download.downloadBitmap(URL)

            val url = "http://www.livroandroid.com.br/livro/carros/carros_classicos.json";
            val json = URL(url).readText()

            // Atualiza o ImageView
            runOnUiThread {
                img.setImageBitmap(bitmap)

                dialog.dismiss()

                Toast.makeText(baseContext, "json " + json, Toast.LENGTH_LONG).show()
            }

        }.start()
    }
}

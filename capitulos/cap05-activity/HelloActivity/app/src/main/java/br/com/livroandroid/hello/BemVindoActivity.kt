package br.com.livroandroid.hello

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView


class BemVindoActivity : DebugActivity() {

    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        // Views
        setContentView(R.layout.activity_bem_vindo)
        val textview = findViewById<TextView>(R.id.text)
        // Args
        val args = intent.extras
        val nome = args.getString("nome")
        textview.text = "$nome, seja bem vindo."

        // Ativa o "up navigation" na action bar
//        actionBar?.setDisplayHomeAsUpEnabled(true)


    }

    /*
    //Como foi configurado o parent=MainActivity no manifest, nao precisa tratar o evento aqui.

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) {
            // Trata o clique no botão de voltar (<--)
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
*/
}

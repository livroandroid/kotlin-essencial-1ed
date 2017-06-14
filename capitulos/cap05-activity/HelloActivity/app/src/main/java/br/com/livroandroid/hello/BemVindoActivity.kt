package br.com.livroandroid.hello

import android.os.Bundle
import android.widget.TextView


class BemVindoActivity : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bem_vindo)

        // Adiciona o botão "up navigation"
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        // Views
        val text = findViewById(R.id.text) as TextView

        // Args
        val args = intent.extras
        val nome = args.getString("nome")

        if(nome != null) {
            text.text = nome + ", seja bem vindo."
        }
    }

    /*
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            // O método finish() vai encerrar essa activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
}

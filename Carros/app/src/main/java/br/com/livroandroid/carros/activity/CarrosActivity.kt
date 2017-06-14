package br.com.livroandroid.carros.activity

import android.os.Bundle
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.fragments.CarrosFragment

class CarrosActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)
        // Configura a toolbar
        setUpToolbar()
        // Mostra o botão voltar “up navigation”
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Mostra o tipo do carro no título
        val tipo = getString(intent.getIntExtra("tipo", 0))
        supportActionBar!!.title = tipo
        // Adiciona o fragment com o mesmo Bundle (args) da intent
        if (savedInstanceState == null) {
            // Cria uma instância do fragment, e configura os argumentos.
            val frag = CarrosFragment()
            // Dentre os argumentos que foram passados para a activity, está o tipo do carro.
            frag.arguments = intent.extras
            // Adiciona o fragment no layout de marcação
            supportFragmentManager.beginTransaction().add(R.id.container, frag).commit()
        }
    }
}

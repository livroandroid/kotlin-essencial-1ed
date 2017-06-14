package br.com.livroandroid.carros.activity

import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView

import com.squareup.picasso.Picasso

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.fragments.CarroFragment

class CarroActivity : BaseActivity() {
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        setContentView(R.layout.activity_carro)
        // Configura a Toolbar como a action bar
        setUpToolbar()
        // Título da Toolbar e botão up navigation
        val c = intent.getParcelableExtra<Parcelable>("carro") as Carro
        supportActionBar!!.setTitle(c.nome)
        // Liga o botão up navigation para voltar.
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Imagem de cabeçalho na AppBar
        val appBarImg = findViewById(R.id.appBarImg) as ImageView
        Picasso.with(context).load(c.urlFoto).into(appBarImg)
        if (b == null) {
            // Cria o fragment com o mesmo Bundle (args) da intent
            val frag = CarroFragment()
            frag.arguments = intent.extras
            // Adiciona o fragment no layout
            supportFragmentManager.beginTransaction().add(R.id.CarroFragment, frag).commit()
        }
    }
}

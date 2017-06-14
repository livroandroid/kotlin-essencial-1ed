package br.com.livroandroid.carros.activity

import android.os.Bundle

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.fragments.SiteLivroFragment

class SiteLivroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)
        setUpToolbar()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(R.string.site_do_livro)
        if (savedInstanceState == null) {
            val frag = SiteLivroFragment()
            supportFragmentManager.beginTransaction().add(R.id.container, frag).commit()
        }
    }
}

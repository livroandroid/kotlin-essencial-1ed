package br.com.livroandroid.carros.activity
import android.os.Bundle
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.fragments.CarrosFragment
import br.com.livroandroid.carros.extensions.addFragment
import br.com.livroandroid.carros.extensions.setupToolbar

class CarrosActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carros)
        // Argumentos: tipo do carro
        val tipo = intent.getSerializableExtra("tipo") as TipoCarro
        val title = getString(tipo.string)
        // Toolbar: configura o título e o "up navigation"
        setupToolbar(R.id.toolbar, title, true)
        if (savedInstanceState == null) {
            // Adiciona o fragment no layout de marcação
            // Dentre os argumentos que foram passados para a activity, está o tipo do carro.
            addFragment(R.id.container, CarrosFragment())
        }
    }
}

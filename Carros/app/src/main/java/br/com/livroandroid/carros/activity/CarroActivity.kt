package br.com.livroandroid.carros.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService
import br.com.livroandroid.carros.domain.FavoritosService
import br.com.livroandroid.carros.domain.event.FavoritoEvent
import br.com.livroandroid.carros.domain.event.SaveCarroEvent
import br.com.livroandroid.carros.extensions.*
import br.com.livroandroid.carros.fragments.MapaFragment
import br.com.livroandroid.carros.utils.HttpHelper.post
import kotlinx.android.synthetic.main.activity_carro.*
import kotlinx.android.synthetic.main.activity_carro_contents.*
import org.greenrobot.eventbus.EventBus
import org.jetbrains.anko.*

class CarroActivity : BaseActivity() {
    val carro by lazy { intent.getParcelableExtra<Carro>("carro") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carro)
        // Seta o nome do carro como título da Toolbar
        setupToolbar(R.id.toolbar, carro.nome, true)
        // Atualiza os dados do carro na tela
        initViews()
        // Variável gerada automaticamente pelo Kotlin Extensions
        fab.setOnClickListener { onClickFavoritar(carro) }
    }

    fun initViews() {
        // Variáveis  geradas automaticamente pelo Kotlin Extensions (veja import)
        tDesc.text = carro.desc
        appBarImg.loadUrl(carro.urlFoto)

        // Foto do Carro
        img.loadUrl(carro.urlFoto)

        // Toca o Vídeo
        imgPlayVideo.setOnClickListener {
            val url = carro.urlVideo
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "video/*")
            startActivity(intent)
        }

        // Adiciona o fragment do Mapa
        val mapaFragment = MapaFragment()
        mapaFragment.setArguments(intent.extras)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.mapaFragment, mapaFragment)
                .commit()
    }

    override fun onResume() {
        super.onResume()
        taskUpdateFavoritoColor()
    }

    // Busca no banco se o carro está favoritado e atualiza a cor do FAB
    private fun taskUpdateFavoritoColor() {
        doAsync {
            val b = FavoritosService.isFavorito(carro)
            uiThread {
                setFavoriteColor(b)
            }
        }
    }

    // Desenha a cor do FAB conforme está favoritado ou não.
    fun setFavoriteColor(favorito: Boolean) {
        // Troca a cor conforme o status do favoritos
        val fundo = ContextCompat.getColor(this, if (favorito) R.color.favorito_on else R.color.favorito_off)
        val cor = ContextCompat.getColor(this, if (favorito) R.color.yellow else R.color.favorito_on)
        fab.setBackgroundTintList(ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(fundo)))
        fab.setColorFilter(cor)
    }

    // Adiciona ou Remove o carro dos Favoritos
    fun onClickFavoritar(carro: Carro) {
        doAsync {
            val favoritado = FavoritosService.favoritar(carro)
            uiThread {
                // Alerta de sucesso
                toast(if (favoritado) R.string.msg_carro_favoritado else R.string.msg_carro_desfavoritado)

                // Atualiza cor do botão FAB
                setFavoriteColor(favoritado)

                // Dispara um evento para atualizar a lista
                EventBus.getDefault().post(FavoritoEvent(carro))
            }
        }
    }

    // Adiciona as opções Salvar e Deletar no menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_carro, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Trata os eventos do menu
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_editar -> {
                startActivity<CarroFormActivity>("carro" to carro)
                finish()
            }
            R.id.action_deletar -> {
                alert(R.string.msg_confirma_excluir_carro, R.string.app_name) {
                    positiveButton(R.string.sim) {
                        // Confirmou o excluir
                        taskExcluir()
                    }
                    negativeButton(R.string.nao) {
                        // Não confirmou...
                    }
                }.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Exclui um carro do servidor
    fun taskExcluir() {
        doAsync {
            val response = CarroService.delete(carro)

            uiThread {
                toast(response.msg)
                finish()

                // Atualiza a lista
                EventBus.getDefault().post(SaveCarroEvent(carro))
            }
        }
    }
}

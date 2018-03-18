package br.com.livroandroid.carros.fragments

import br.com.livroandroid.carros.activity.CarroActivity
import br.com.livroandroid.carros.adapter.CarroAdapter
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.FavoritosService
import br.com.livroandroid.carros.domain.event.FavoritoEvent
import kotlinx.android.synthetic.main.fragment_carros.*
import org.greenrobot.eventbus.Subscribe
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class FavoritosFragment : CarrosFragment() {

    @Suppress("UNUSED_PARAMETER")
    @Subscribe
    fun onRefresh(event: FavoritoEvent) {
        taskCarros()
    }

    override fun taskCarros() {
        doAsync {
            // Busca os carros
            carros = FavoritosService.getCarros()
            uiThread {
                recyclerView.adapter = CarroAdapter(carros) { onClickCarro(it) }

                // Termina a animação do Swipe to Refresh
                swipeToRefresh.isRefreshing = false
            }
        }
    }

    override fun onClickCarro(carro: Carro) {
        // Ao clicar no carro vamos navegar para a tela de detalhes
        activity?.startActivity<CarroActivity>("carro" to carro)
    }
}

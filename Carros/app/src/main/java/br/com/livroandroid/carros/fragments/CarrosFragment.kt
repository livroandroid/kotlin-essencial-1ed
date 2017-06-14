package br.com.livroandroid.carros.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.squareup.otto.Subscribe

import java.io.IOException

import br.com.livroandroid.carros.CarrosApplication
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.activity.CarroActivity
import br.com.livroandroid.carros.adapter.CarroAdapter
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService


class CarrosFragment : BaseFragment() {
    protected var recyclerView: RecyclerView
    // Tipo do carro passado pelos argumentos
    private var tipo: Int = 0
    // Lista de carros
    private var carros: List<Carro>? = null
    private val dialog: ProgressDialog? = null
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        if (arguments != null) {
            // Lê o tipo dos argumentos
            this.tipo = arguments.getInt("tipo")
        }        // Registra a classe para receber eventos
        CarrosApplication.instance.bus.register(this)


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, b: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_carros, container, false)
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        return view
    }

    override fun onActivityCreated(b: Bundle?) {
        super.onActivityCreated(b)
        taskCarros()
    }

    private fun taskCarros() {
        // Busca os carros: Dispara a Task
        GetCarrosTask().execute()
    }

    // Task para buscar os carros
    private inner class GetCarrosTask : AsyncTask<Void, Void, List<Carro>>() {
        override fun doInBackground(vararg params: Void): List<Carro>? {
            try {
                // Busca os carros em background (Thread)
                return CarroService.getCarros(context, tipo)
            } catch (e: IOException) {
                alert("Erro: " + e.message)
                return null
            }

        }

        // Atualiza a interface
        override fun onPostExecute(carros: List<Carro>?) {
            if (carros != null) {
                this@CarrosFragment.carros = carros
                // Atualiza a view na UI Thread
                recyclerView.adapter = CarroAdapter(context, carros, onClickCarro())
            }
        }
    }

    // Da mesma forma que tratamos o evento de clique em um botão (OnClickListener)
    // Vamos tratar o evento de clique na lista.
    // A diferença é que a interface CarroAdapter.CarroOnClickListener nós mesmo criamos.
    private fun onClickCarro(): CarroAdapter.CarroOnClickListener {
        return CarroAdapter.CarroOnClickListener { view, idx ->
            // Carro selecionado.
            val c = carros!![idx]
            // Mostra um alerta rápido com um toast.
            val intent = Intent(context, CarroActivity::class.java)
            intent.putExtra("carro", c)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancela o recebimento de eventos
        CarrosApplication.instance.bus.unregister(this)
    }

    @Subscribe
    fun onBusAtualizarListaCarros(refresh: String) {
        // Recebeu o evento, atualiza a lista
        taskCarros()
    }

    companion object {

        // Método para instanciar esse fragment pelo tipo
        fun newInstance(tipo: Int): CarrosFragment {
            val args = Bundle()
            args.putInt("tipo", tipo)
            val f = CarrosFragment()
            f.arguments = args
            return f
        }
    }

}


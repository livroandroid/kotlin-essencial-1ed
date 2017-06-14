package br.com.livroandroid.carros.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.squareup.picasso.Picasso

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro

/**
 * Created by rlech on 10-Dec-15.
 */
class CarroAdapter// Construtor, geralmente recebe o contexto, a lista e a implementação da interface de callback.
(private val context: Context, // Lista de carros
 private val carros: List<Carro>?, // Interface de callback para expor os eventos de toque na lista
 private val carroOnClickListener: CarroAdapter.CarroOnClickListener?) : RecyclerView.Adapter<CarroAdapter.CarrosViewHolder>() {

    override fun getItemCount(): Int {
        // Retorna a quantidade de linhas da lista (geralmente é a quantidade de elementos do array)
        // Uma view será criada para cada linha.
        return if (this.carros != null) this.carros.size else 0
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CarrosViewHolder {
        // Este método deve inflar a view e criar um ViewHolder.
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_carro, viewGroup, false)
        // Depois de inflar a view (R.layout.adapter_carro), cria o ViewHolder.
        // O ViewHolder contém a referência para todas as views do layout.
        val holder = CarrosViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CarrosViewHolder, position: Int) {
        // Recupera o carro da linha x
        val c = carros!![position]
        // Faz a ligação (bind) das informações do carro, com as views do layout.
        holder.tNome.text = c.nome
        holder.progress.visibility = View.VISIBLE
        // Faz o download da foto e mostra o ProgressBar
        Picasso.with(context).load(c.urlFoto).fit().into(holder.img,
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        // Download Ok, então esconde o progress.
                        holder.progress.visibility = View.GONE
                    }

                    override fun onError() {
                        holder.progress.visibility = View.GONE
                    }
                })
        // Click
        if (carroOnClickListener != null) {
            holder.itemView.setOnClickListener {
                // Ao clicar na view da lista, chama a interface de callback (listener)
                // Obs: A variável position é final
                carroOnClickListener.onClickCarro(holder.itemView, position)
            }
        }
    }

    // Interface de callback para expor os eventos da lista.
    interface CarroOnClickListener {
        fun onClickCarro(view: View, idx: Int)
    }

    // O ViewHolder possui a referência para as views do layout
    class CarrosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tNome: TextView
        internal var img: ImageView
        internal var progress: ProgressBar
        internal var cardView: CardView

        init {
            // Faz o findViewById(id) para armazenar as views
            // O Android vai reutilizar este ViewHolder durante a rolagem
            tNome = view.findViewById(R.id.text) as TextView
            img = view.findViewById(R.id.img) as ImageView
            progress = view.findViewById(R.id.progressImg) as ProgressBar
            cardView = view.findViewById(R.id.card_view) as CardView
        }
    }

    companion object {
        protected val TAG = "livroandroid"
    }
}

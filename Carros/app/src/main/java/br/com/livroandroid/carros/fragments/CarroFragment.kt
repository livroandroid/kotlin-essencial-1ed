package br.com.livroandroid.carros.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import br.com.livroandroid.carros.CarrosApplication
import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroDB
import livroandroid.lib.task.TaskListener

class CarroFragment : BaseFragment() {
    private var carro: Carro? = null
    private var fab: FloatingActionButton? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, b: Bundle?): View? {
        // Infla a view deste fragment.
        val view = inflater!!.inflate(R.layout.fragment_carro, container, false)
        // Lê o objeto carro dos parâmetros
        carro = arguments.getParcelable<Carro>("carro")
        // Atualiza a descrição do carro no TextView
        val tDesc = view.findViewById(R.id.tDesc) as TextView
        tDesc.text = carro!!.desc

        // Favorito
        fab = activity.findViewById(R.id.fab) as FloatingActionButton
        fab!!.setOnClickListener { startTask("favorito", taskFavoritar()) }

        // Video
        view.findViewById(R.id.imgPlayVideo).setOnClickListener(onClickPlayVideo())

        // Mapa
        val mapaFragment = MapaFragment()
        mapaFragment.arguments = arguments
        childFragmentManager.beginTransaction().replace(R.id.mapaFragment, mapaFragment).commit()

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Verifica se o carro está favoritado e troca a cor do botão FAB.
        startTask("checkFavorito", checkFavorito())
    }

    private fun checkFavorito(): TaskListener<*> {
        return object : BaseFragment.BaseTask<Boolean>() {
            @Throws(Exception::class)
            override fun execute(): Boolean? {
                val db = CarroDB(context)
                // Verifica se este carro já foi salvo.
                val exists = db.exists(carro!!.nome)
                return exists
            }

            override fun updateView(exists: Boolean?) {
                setFabColor(exists)
            }
        }
    }

    private fun taskFavoritar(): TaskListener<*> {
        return object : BaseFragment.BaseTask<Boolean>() {
            @Throws(Exception::class)
            override fun execute(): Boolean? {
                val db = CarroDB(context)
                // Verifica se este carro já foi salvo.
                val exists = db.exists(carro!!.nome)
                if (!exists) {
                    // Adiciona nos favoritos
                    db.save(carro)
                    return true
                } else {
                    // Remove dos favoritos
                    db.delete(carro)
                    return false
                }
            }

            override fun updateView(favoritou: Boolean?) {
                // Mostra a msg na UI Thread.
                if (favoritou!!) {
                    snack(view, "Carro adicionado aos favoritos")
                } else {
                    snack(view, "Carro removido dos favoritos")
                }
                setFabColor(favoritou)

                // Envia o evento para o bus depois de clicar no botão FAB
                CarrosApplication.instance.bus.post("refresh")
            }
        }
    }

    private fun setFabColor(favorito: Boolean?) {
        if (favorito!!) {
            // Cor do botão FAB se está favoritado
            fab!!.imageTintList = ContextCompat.getColorStateList(context, R.color.accent)
            fab!!.backgroundTintList = ContextCompat.getColorStateList(context, R.color.yellow)
        } else {
            // Cor do botão FAB  se não está favoritado
            fab!!.imageTintList = ContextCompat.getColorStateList(context, R.color.accent)
            fab!!.backgroundTintList = ContextCompat.getColorStateList(context, R.color.gray)
        }
    }

    private fun onClickPlayVideo(): View.OnClickListener {
        return View.OnClickListener {
            // Intent para tocar o vídeo no player nativo
            val url = carro!!.urlVideo
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setDataAndType(Uri.parse(url), "video/*")
            startActivity(intent)
        }
    }

}

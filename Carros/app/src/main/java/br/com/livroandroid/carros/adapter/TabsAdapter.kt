package br.com.livroandroid.carros.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.livroandroid.carros.domain.TipoCarro
import br.com.livroandroid.carros.fragments.CarrosFragment

import br.com.livroandroid.carros.fragments.FavoritosFragment

class TabsAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    // Qtde de Tabs
    override fun getCount() = 4

    // Retorna o tipo pela posição
    fun getTipoCarro(position: Int) = when (position) {
        0 -> TipoCarro.classicos
        1 -> TipoCarro.esportivos
        2 -> TipoCarro.luxo
        else -> TipoCarro.favoritos
    }

    // Título da Tab
    override fun getPageTitle(position: Int): CharSequence {
        val tipo = getTipoCarro(position)
        return context.getString(tipo.string)
    }

    // Fragment com a lista de carros
    override fun getItem(position: Int): Fragment {
        if (position == 3) {
            // Favoritos
            return FavoritosFragment()
        }
        // Clássicos, Esportivos e Luxo
        val tipo = getTipoCarro(position)
        val f: Fragment = CarrosFragment()
        f.arguments = Bundle()
        f.arguments.putSerializable("tipo", tipo)
        return f
    }
}
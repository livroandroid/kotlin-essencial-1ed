package br.com.livroandroid.carros.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.fragments.CarrosFragment

class TabsAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence {
        if (position == 0) {
            return context.getString(R.string.classicos)
        } else if (position == 1) {
            return context.getString(R.string.esportivos)
        } else if (position == 2) {
            return context.getString(R.string.luxo)
        }
        return context.getString(R.string.favoritos)
    }

    override fun getItem(position: Int): Fragment {
        var f: Fragment? = null
        if (position == 0) {
            f = CarrosFragment.newInstance(R.string.classicos)
        } else if (position == 1) {
            f = CarrosFragment.newInstance(R.string.esportivos)
        } else if (position == 2) {
            f = CarrosFragment.newInstance(R.string.luxo)
        } else {
            f = CarrosFragment.newInstance(R.string.favoritos)
        }
        return f
    }
}


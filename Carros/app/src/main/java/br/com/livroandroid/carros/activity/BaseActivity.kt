package br.com.livroandroid.carros.activity

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import br.com.livroandroid.carros.R

open class BaseActivity : livroandroid.lib.activity.BaseActivity() {
    protected var drawerLayout: DrawerLayout? = null

    // Configura a Toolbar
    protected fun setUpToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    // Configura o nav drawer
    protected fun setupNavDrawer() {
        // Drawer Layout
        val actionBar = supportActionBar
        // Ícone do menu do Nav Drawer
        actionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu)
        actionBar.setDisplayHomeAsUpEnabled(true)
        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        val navigationView = findViewById(R.id.nav_view) as NavigationView
        if (navigationView != null && drawerLayout != null) {
            // Atualiza a imagem e os textos do header
            setNavViewValues(navigationView, R.string.nav_drawer_username, R.string.nav_drawer_email, R.mipmap.ic_launcher)
            // Trata o evento de clique no menu
            navigationView.setNavigationItemSelectedListener { menuItem ->
                // Seleciona a linha
                //menuItem.setChecked(true);
                // Fecha o menu
                drawerLayout!!.closeDrawers()
                // Trata o evento do menu
                onNavDrawerItemSelected(menuItem)
                true
            }
        }
    }

    // Trata o evento do menu lateral
    // Trata o evento do menu lateral
    private fun onNavDrawerItemSelected(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_item_carros_todos -> toast("Clicou em carros")
            R.id.nav_item_carros_classicos -> {
                var intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", R.string.classicos)
                startActivity(intent)
            }
            R.id.nav_item_carros_esportivos -> {
                intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", R.string.esportivos)
                startActivity(intent)
            }
            R.id.nav_item_carros_luxo -> {
                intent = Intent(context, CarrosActivity::class.java)
                intent.putExtra("tipo", R.string.luxo)
                startActivity(intent)
            }
            R.id.nav_item_site_livro -> startActivity(Intent(context, SiteLivroActivity::class.java))
            R.id.nav_item_settings -> toast("Clicou em configurações")
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                // Trata o clique no botão que abre o menu
                if (drawerLayout != null) {
                    openDrawer()
                    return true
                }
        }
        return super.onOptionsItemSelected(item)
    }

    // Abre o menu lateral
    protected fun openDrawer() {
        if (drawerLayout != null) {
            drawerLayout!!.openDrawer(GravityCompat.START)
        }
    }

    // Fecha o menu lateral
    protected fun closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        }
    }

    companion object {

        // Atualiza os dados do header do Navigation View
        fun setNavViewValues(navView: NavigationView, nome: Int, email: Int, img: Int) {
            val headerView = navView.getHeaderView(0)
            val tNome = headerView.findViewById(R.id.tNome) as TextView
            val tEmail = headerView.findViewById(R.id.tEmail) as TextView
            val imgView = headerView.findViewById(R.id.img) as ImageView
            tNome.setText(nome)
            tEmail.setText(email)
            imgView.setImageResource(img)
        }
    }
}

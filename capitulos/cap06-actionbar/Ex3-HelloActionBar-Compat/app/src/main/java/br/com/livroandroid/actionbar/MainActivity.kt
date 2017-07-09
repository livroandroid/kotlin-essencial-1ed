package br.com.livroandroid.actionbar

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.support.v7.widget.ShareActionProvider
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : android.support.v7.app.AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "Hello ActionBarCompat"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Infla o menu com os botões da action bar
        menuInflater.inflate(R.menu.menu_main, menu)

        // SearchView
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(onSearch())

        // ShareActionProvider
        val shareItem = menu.findItem(R.id.action_share)
        val share = MenuItemCompat.getActionProvider(shareItem) as ShareActionProvider
        // Intent que define o conteúdo que será compartilhado
        val defaultIntent = Intent(Intent.ACTION_SEND)
        defaultIntent.type = "text/*"
        defaultIntent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar")
        share.setShareIntent(defaultIntent)
        return true
    }

    private fun onSearch(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                toast("Buscar o texto: " + query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_search) {
            toast("Clicou no Search!")
            return true
        } else if (id == R.id.action_refresh) {
            toast("Clicou no Refresh!")
            return true
        } else if (id == R.id.action_settings) {
            toast("Clicou no Settings!")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}

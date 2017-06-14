package br.com.livroandroid.actionbar

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.ShareActionProvider
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = actionBar
        actionBar?.title = "Hello ActionBar"
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
        val share = shareItem.actionProvider as ShareActionProvider
        share.setShareIntent(defaultIntent)

        return true
    }

    // Intent que define o conteúdo que será compartilhado
    private val defaultIntent: Intent
        get() {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/*"
            intent.putExtra(Intent.EXTRA_TEXT, "Texto para compartilhar")
            return intent
        }

    private fun onSearch(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Usuário fez a busca
                toast("Buscar o texto: " + query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Mudou o texto digitado
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

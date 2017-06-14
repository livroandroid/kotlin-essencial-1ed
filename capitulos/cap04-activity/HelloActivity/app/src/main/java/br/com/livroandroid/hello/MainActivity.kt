package br.com.livroandroid.hello


import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import toast

class MainActivity : DebugActivity() {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btLogin = findViewById(R.id.btLogin) as Button
        //btLogin.setOnClickListener { onClickLogin() }
    }

    fun onClickLogin() {
        toast("click!!!!!!")
    }

    /*fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }*/

    /*private fun onClickLogin(): View.OnClickListener {
        return View.OnClickListener {
            val tLogin = findViewById(R.id.tLogin) as TextView
            val tSenha = findViewById(R.id.tSenha) as TextView
            val login = tLogin.text.toString()
            val senha = tSenha.text.toString()

            if ("ricardo" == login && "123" == senha) {
                // Navega para a próxima tela

                val intent = Intent(context, BemVindoActivity::class.java)
                val params = Bundle()
                params.putString("nome", "Ricardo Lecheta")
                intent.putExtras(params)
                startActivity(intent)
            } else {
                toast("Login e senha incorretos.")
            }
        }
    }*/

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Infla o menu da action bar (/res/menu/main.xml)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            // Clicou no botão settings da action bar (o toast é um breve alerta que vai sumir)
            toast("Clicou no settings")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}

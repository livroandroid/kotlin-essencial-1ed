package br.com.livroandroid.hellofragments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Adiciona o fragment dinamicamente pela API
        if (savedInstanceState == null) {
            // Adiciona fragment no layout
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            val frag1 = HelloFragment()
            ft.add(R.id.layoutFrag, frag1, "HelloFragment")
            ft.commit()

            // OnClick para demonstrar como chamar método do fragment
            findViewById(R.id.btHello).setOnClickListener {
                val fm = supportFragmentManager
                val frag1 = fm.findFragmentByTag("HelloFragment") as HelloFragment
                frag1.hello()
            }
        }
    }
}


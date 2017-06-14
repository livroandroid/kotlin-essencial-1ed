package br.com.livroandroid.carros.activity

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.view.View

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.adapter.TabsAdapter
import livroandroid.lib.utils.PermissionUtils
import livroandroid.lib.utils.Prefs

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar()
        setupNavDrawer()
        setupViewPagerTabs()
        // FAB
        findViewById(R.id.fab).setOnClickListener { v -> snack(v, "Exemplo de FAB Button.") }

        // Solicita as permissões
        val permissoes = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
        PermissionUtils.validate(this, 0, *permissoes)
    }

    // Configura o ViewPager + Tabs
    private fun setupViewPagerTabs() {
        // ViewPager
        val viewPager = findViewById(R.id.viewPager) as ViewPager
        viewPager.offscreenPageLimit = 2
        viewPager.adapter = TabsAdapter(context, supportFragmentManager)
        // Tabs
        val tabLayout = findViewById(R.id.tabLayout) as TabLayout
        // Cria as tabs com o mesmo adapter utilizado pelo ViewPager
        tabLayout.setupWithViewPager(viewPager)
        val cor = ContextCompat.getColor(context, R.color.white)
        // Cor branca no texto (o fundo azul foi definido no layout)
        tabLayout.setTabTextColors(cor, cor)

        // Lê o índice da última tab utilizada no aplicativo
        val tabIdx = Prefs.getInteger(context, "tabIdx")
        viewPager.currentItem = tabIdx
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float,
                                        positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                // Salva o índice da página/tab selecionada
                Prefs.setInteger(context, "tabIdx", viewPager.currentItem)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                // Alguma permissão foi negada, agora é com você :-)
                alertAndFinish()
                return
            }
        }

        // Se chegou aqui está OK :-)
    }

    private fun alertAndFinish() {
        run {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(R.string.app_name).setMessage("Para utilizar este aplicativo, você precisa aceitar as permissões.")
            // Add the buttons
            builder.setPositiveButton("OK") { dialog, id -> finish() }
            val dialog = builder.create()
            dialog.show()

        }
    }
}

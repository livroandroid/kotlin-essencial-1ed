package br.com.livroandroid.carros.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.fragments.dialog.AboutDialog

class SiteLivroFragment : BaseFragment() {
    private var webview: WebView? = null
    private var progress: ProgressBar? = null
    protected var swipeLayout: SwipeRefreshLayout
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_site_livro, container, false)
        webview = view.findViewById(R.id.webview) as WebView
        progress = view.findViewById(R.id.progress) as ProgressBar
        setWebViewClient(webview)
        // Carrega a página
        webview!!.loadUrl(URL_SOBRE)
        // Swipe to Refresh
        swipeLayout = view.findViewById(R.id.swipeToRefresh) as SwipeRefreshLayout
        swipeLayout.setOnRefreshListener(OnRefreshListener())
        // Cores da animação
        swipeLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3)
        return view
    }

    private fun OnRefreshListener(): SwipeRefreshLayout.OnRefreshListener {
        return SwipeRefreshLayout.OnRefreshListener {
            webview!!.reload() // Atualiza a página
        }
    }


    private fun setWebViewClient(webview: WebView) {
        webview.setWebViewClient(object : WebViewClient() {
            override fun onPageStarted(webview: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(webview, url, favicon)
                // Liga o progress
                progress!!.visibility = View.VISIBLE
            }

            override fun onPageFinished(webview: WebView, url: String) {
                // Desliga o progress
                progress!!.visibility = View.INVISIBLE
                // Termina a animação do Swipe to Refresh
                swipeLayout.isRefreshing = false

            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                Log.d("livroandroid", "webview url: " + url!!)
                if (url != null && url.endsWith("sobre.htm")) {
                    AboutDialog.showAbout(fragmentManager)
                    // Retorna true para informar que interceptamos o evento
                    return true
                }
                return super.shouldOverrideUrlLoading(view, url)
            }

        })
    }

    companion object {
        private val URL_SOBRE = "http://www.livroandroid.com.br/sobre.htm"
    }
}

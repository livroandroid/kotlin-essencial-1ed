package br.com.livroandroid.carros.utils
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object AndroidUtils {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivity.getAllNetworks()
        for (n in networks) {
            val info = connectivity.getNetworkInfo(n)
            if (info.state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false;
    }
}

package br.com.livroandroid.helloservice
import android.app.IntentService
import android.content.Intent
import android.util.Log
import br.com.livroandroid.hellonotification.NotificationUtil

class HelloService : IntentService("LivroAndroid") {
    protected var count: Int = 0
    private var running: Boolean = false
    // Constantes
    private val MAX = 10
    private val TAG = "livro"
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, ">> HelloService.onHandleIntent()")
        running = true
        while (running && count < MAX) {
            // Simula algum processamento
            Thread.sleep(1000)
            Log.d(TAG, "HelloService executando... " + count)
            count++
        }
        Log.d(TAG, "<< HelloService.onHandleIntent()")
        val it = Intent(this, MainActivity::class.java)
        NotificationUtil.create(this, 1, it, "Livro Android", "Fim do serviço.")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "HelloService.onDestroy()")
        running = false
    }
}

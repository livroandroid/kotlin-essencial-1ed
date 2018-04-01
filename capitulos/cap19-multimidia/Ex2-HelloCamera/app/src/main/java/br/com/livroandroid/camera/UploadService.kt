package br.com.livroandroid.camera

import android.util.Base64
import android.util.Log
import br.com.livroandroid.camera.utils.HttpHelper
import com.google.gson.Gson
import java.io.File

object UploadService {
    private val URL_BASE = "http://livrowebservices.com.br/rest/carros"
    private val TAG = "livroandroid"

    fun upload(file: File): Response {

        // Converte para Base64
        val bytes = file.readBytes()
        val base64 = Base64.encodeToString(bytes, Base64.NO_WRAP)
        Log.d(TAG, "base64: $base64")

        // Faz POST
        val url = "$URL_BASE/postFotoBase64"
        val params = HashMap<String, String>()
        params["fileName"] = file.getName()
        params["base64"] = base64
        val json = HttpHelper.postForm(url, params)
        Log.d(TAG, "response: $json")

        // Converte o JSON de resposta para objeto
        val response = Gson().fromJson(json, Response::class.java)
        return response
    }
}

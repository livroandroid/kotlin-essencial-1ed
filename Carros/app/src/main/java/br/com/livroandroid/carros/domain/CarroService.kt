package br.com.livroandroid.carros.domain

import android.util.Base64
import br.com.livroandroid.carros.CarrosApplication
import br.com.livroandroid.carros.domain.dao.DatabaseManager
import br.com.livroandroid.carros.extensions.fromJson
import br.com.livroandroid.carros.extensions.toJson
import br.com.livroandroid.carros.utils.HttpHelper
import java.io.File

/**
 * Implementação com OkHttp
 */
object CarroService {
    private val BASE_URL = "http://livrowebservices.com.br/rest/carros"

    // Busca os carros por tipo (clássicos, esportivos ou luxo)
    fun getCarros(tipo: TipoCarro): List<Carro> {
        val url = "$BASE_URL/tipo/${tipo.name}"
        val json = HttpHelper.get(url)
        val carros = fromJson<List<Carro>>(json)
        return carros
    }

    // Salva um carro
    fun save(carro: Carro): Response {
        // Faz POST do JSON carro
        val json = HttpHelper.post(BASE_URL, carro.toJson())
        val response = fromJson<Response>(json)
        return response
    }

    // Deleta um carro
    fun delete(carro: Carro): Response {
        val url = "$BASE_URL/${carro.id}"
        val json = HttpHelper.delete(url)
        val response = fromJson<Response>(json)
        if(response.isOk()) {
            // Se removeu do servidor, remove dos favoritos
            val dao = DatabaseManager.getCarroDAO()
            dao.delete(carro)
        }
        return response
    }

    fun postFoto(file: File): Response {
        val url = "$BASE_URL/postFotoBase64"

        // Converte para Base64
        val bytes = file.readBytes()
        val base64 = Base64.encodeToString(bytes, Base64.NO_WRAP)

        val params = mapOf("fileName" to file.name, "base64" to base64)

        val json = HttpHelper.postForm(url, params)

        val response = fromJson<Response>(json)

        return response
    }
}
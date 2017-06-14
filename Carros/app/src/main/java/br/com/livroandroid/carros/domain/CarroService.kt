package br.com.livroandroid.carros.domain

import android.content.Context

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.io.IOException
import java.lang.reflect.Type
import java.util.ArrayList

import br.com.livroandroid.carros.R
import livroandroid.lib.utils.HttpHelper

object CarroService {

    private val URL = "http://livrowebservices.com.br/rest/carros"
    private val TAG = CarroService::class.java!!.getSimpleName()

    @Throws(IOException::class)
    fun getCarros(context: Context, tipo: Int): List<Carro> {
        var carros: List<Carro>? = null
        if (tipo == R.string.favoritos) {
            // Consulta no banco de dados
            val db = CarroDB(context)
            carros = db.findAll()
        } else {
            // Consulta no web service.
            val tipoString = getTipo(tipo)
            val url = URL + "/tipo/" + tipoString
            // Faz a requisição HTTP no servidor e retorna a string com o conteúdo.
            val http = HttpHelper()
            http.LOG_ON = true
            // GET
            val json = http.doGet(url)
            // Parser JSON
            carros = parserJSON(context, json)
        }
        return carros
    }

    @Throws(IOException::class)
    private fun parserJSON(context: Context, json: String): List<Carro> {
        val listType = object : TypeToken<ArrayList<Carro>>() {

        }.type
        val carros = Gson().fromJson<List<Carro>>(json, listType)
        return carros
    }

    // Converte a constante para string, para criar a URL do web service.
    private fun getTipo(tipo: Int): String {
        if (tipo == R.string.classicos) {
            return "classicos"
        } else if (tipo == R.string.esportivos) {
            return "esportivos"
        }
        return "luxo"
    }
}
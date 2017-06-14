package br.com.livroandroid.carros.domain

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

import java.util.ArrayList

class CarroDB(context: Context)// context, nome do banco, factory, versão
    : SQLiteOpenHelper(context, CarroDB.NOME_BANCO, null, CarroDB.VERSAO_BANCO) {

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "Criando a Tabela carro...")
        db.execSQL("create table if not exists carro (_id integer primary key autoincrement, nome text, desc text, url_foto text, url_video text, latitude text, longitude text, tipo text); ")
        Log.d(TAG, "Tabela carro criada com sucesso.")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Caso mude a versão do banco de dados, podemos executar um SQL aqui
    }

    // Insere um novo carro, ou atualiza se já existe
    fun save(carro: Carro): Long {
        var id = carro.id
        val db = writableDatabase
        try {
            val values = ContentValues()
            values.put("nome", carro.nome)
            values.put("desc", carro.desc)
            values.put("url_foto", carro.urlFoto)
            values.put("url_video", carro.urlVideo)
            values.put("latitude", carro.latitude)
            values.put("longitude", carro.longitude)
            values.put("tipo", carro.tipo)
            if (id != 0) {
                val _id = carro.id.toString()
                val whereArgs = arrayOf(_id)
                // update carro set values = ... where _id=?
                val count = db.update("carro", values, "_id=?", whereArgs)
                return if (count > 0) id else 0L
            } else {
                // insert into carro values (...)
                id = db.insert("carro", "", values)
                return id
            }
        } finally {
            db.close()
        }
    }

    // Deleta o carro
    fun delete(carro: Carro): Int {
        val db = writableDatabase
        try {
            // delete from carro where _id=?
            val count = db.delete("carro", "_id=?", arrayOf(carro.id.toString()))
            Log.i(TAG, "Deletou [$count] registro.")
            return count
        } finally {
            db.close()
        }
    }

    // Consulta a lista com todos os carros
    fun findAll(): List<Carro> {
        val db = readableDatabase
        try {
            // select * from carro
            val c = db.query("carro", null, null, null, null, null, null, null)
            return toList(c)
        } finally {
            db.close()
        }
    }

    fun exists(nome: String): Boolean {
        val db = readableDatabase
        try {
            // select * from carro
            val c = db.query("carro", null, "nome=?", arrayOf(nome), null, null, null, null)
            val exists = c.count > 0
            return exists
        } finally {
            db.close()
        }
    }

    // Lê o cursor e cria a lista de carros
    private fun toList(c: Cursor): List<Carro> {
        val carros = ArrayList<Carro>()
        if (c.moveToFirst()) {
            do {
                val carro = Carro()
                carros.add(carro)
                // recupera os atributos de carro
                carro.id = c.getLong(c.getColumnIndex("_id"))
                carro.nome = c.getString(c.getColumnIndex("nome"))
                carro.desc = c.getString(c.getColumnIndex("desc"))
                carro.urlFoto = c.getString(c.getColumnIndex("url_foto"))
                carro.urlVideo = c.getString(c.getColumnIndex("url_video"))
                carro.latitude = c.getString(c.getColumnIndex("latitude"))
                carro.longitude = c.getString(c.getColumnIndex("longitude"))
                carro.tipo = c.getString(c.getColumnIndex("tipo"))
            } while (c.moveToNext())
        }
        return carros
    }

    // Executa um SQL
    fun execSQL(sql: String) {
        val db = writableDatabase
        try {
            db.execSQL(sql)
        } finally {
            db.close()
        }
    }

    // Executa um SQL
    fun execSQL(sql: String, args: Array<Any>) {
        val db = writableDatabase
        try {
            db.execSQL(sql, args)
        } finally {
            db.close()
        }
    }

    companion object {
        private val TAG = "sql"
        // Nome do banco
        val NOME_BANCO = "livro_android.sqlite"
        private val VERSAO_BANCO = 1
    }


}

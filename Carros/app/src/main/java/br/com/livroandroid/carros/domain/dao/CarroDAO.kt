package br.com.livroandroid.carros.domain.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.livroandroid.carros.domain.Carro

@Dao
interface CarroDAO {
    @Query("SELECT * FROM carro where id = :arg0")
    fun getById(id: Long): Carro?

    @Query("SELECT * FROM carro")
    fun findAll(): List<Carro>

    @Insert
    fun insert(carro: Carro)

    @Delete
    fun delete(carro: Carro)
}

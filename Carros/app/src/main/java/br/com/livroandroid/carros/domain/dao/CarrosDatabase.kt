package br.com.livroandroid.carros.domain.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.livroandroid.carros.domain.Carro

// Define as classes que precisam ser persistidas e a versão do banco
@Database(entities = arrayOf(Carro::class), version = 1)
abstract class CarrosDatabase : RoomDatabase() {
    abstract fun carroDAO(): CarroDAO
}

package br.com.livroandroid.carros.domain.dao

import android.arch.persistence.room.Room
import br.com.livroandroid.carros.CarrosApplication

/**
 * Created by rlecheta on 17/06/17.
 */
object DatabaseManager {
    // Singleton do Room: banco de dados
    private var dbInstance: CarrosDatabase

    init {
        val appContext = CarrosApplication.getInstance().applicationContext

        // Configura o Room
        dbInstance = Room.databaseBuilder(
                appContext,
                CarrosDatabase::class.java,
                "carros.sqlite")
                .build()
    }

    fun getCarroDAO(): CarroDAO {
        return dbInstance.carroDAO()
    }
}
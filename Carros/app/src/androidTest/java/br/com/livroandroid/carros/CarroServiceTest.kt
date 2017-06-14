package br.com.livroandroid.carros

import android.test.AndroidTestCase

import java.io.IOException

import br.com.livroandroid.carros.domain.Carro
import br.com.livroandroid.carros.domain.CarroService

class CarroServiceTest : AndroidTestCase() {
    @Throws(IOException::class)
    fun testGetCarros() {
        val carros = CarroService.getCarros(context, R.string.esportivos)
        Assert.assertNotNull(carros)
        // Precisa retornar dez carros esportivos.
        Assert.assertTrue(carros.size == 10)
        // Valida as informações do 1º carro
        val c0 = carros[0]
        Assert.assertEquals("Ferrari FF", c0.nome)
        Assert.assertEquals("44.532218", c0.latitude)
        Assert.assertEquals("10.864019", c0.longitude)
        // Valida as informações do último carro
        val c9 = carros[9]
        Assert.assertEquals("MERCEDES-BENZ C63 AMG", c9.nome)
        Assert.assertEquals("-23.564224", c9.latitude)
        Assert.assertEquals("-46.653156", c9.longitude)
    }
}

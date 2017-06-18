package br.com.livroandroid.carros.domain.event

import br.com.livroandroid.carros.domain.Carro

data class SaveCarroEvent(val carro: Carro)

data class FavoritoEvent(val carro: Carro)

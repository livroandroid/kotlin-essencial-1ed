package br.com.livroandroid.carros.domain
import br.com.livroandroid.carros.CarrosApplication
import br.com.livroandroid.carros.domain.dao.DatabaseManager

object FavoritosService {
    // Retorna todos os carros favoritados
    fun getCarros(): List<Carro> {
        val dao = DatabaseManager.getCarroDAO()
        val carros = dao.findAll()
        return carros
    }
    // Verifica se um carro está favoritado
    fun isFavorito(carro: Carro) : Boolean {
        val dao = DatabaseManager.getCarroDAO()
        val exists = dao.getById(carro.id) != null
        return exists
    }

    // Salva o carro ou deleta
    fun favoritar(carro: Carro): Boolean {
        val favorito = isFavorito(carro)
        val dao = DatabaseManager.getCarroDAO()
        if(favorito) {
            // Remove dos favoritos
            dao.delete(carro)
            return false
        }
        // Adiciona nos favoritos
        dao.insert(carro)
        return true
    }

}

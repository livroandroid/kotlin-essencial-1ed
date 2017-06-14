package br.com.livroandroid.carros.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import br.com.livroandroid.carros.R
import br.com.livroandroid.carros.domain.Carro

/**
 * A simple [Fragment] subclass.
 */
class MapaFragment : BaseFragment(), OnMapReadyCallback {
    // Objeto que controla o Google Maps
    private var map: GoogleMap? = null
    private var carro: Carro? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, b: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_mapa, container, false)
        // Recupera o fragment que está no layout
        // Utiliza o getChildFragmentManager() pois é um fragment dentro do outro
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        // Inicia o Google Maps dentro do fragment
        mapFragment.getMapAsync(this)
        this.carro = arguments.getParcelable<Parcelable>("carro") as Carro
        return view
    }

    override fun onMapReady(map: GoogleMap) {
        // O método onMapReady(map) é chamado quando a inicialização do mapa estiver Ok.
        this.map = map
        if (carro != null) {
            // Ativa o botão para mostrar minha localização
            //map.setMyLocationEnabled(true);
            // Cria o objeto LatLng com a coordenada da fábrica
            val location = LatLng(java.lang.Double.parseDouble(carro!!.latitude),
                    java.lang.Double.parseDouble(carro!!.longitude))
            // Posiciona o mapa na coordenada da fábrica (zoom = 13)
            val update = CameraUpdateFactory.newLatLngZoom(location, 13f)
            map.moveCamera(update)
            // Marcador no local da fábrica
            map.addMarker(MarkerOptions().title(carro!!.nome).snippet(carro!!.desc).position(location))
            // Tipo do mapa:
            // (normal, satélite, terreno ou híbrido)
            map.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }
}

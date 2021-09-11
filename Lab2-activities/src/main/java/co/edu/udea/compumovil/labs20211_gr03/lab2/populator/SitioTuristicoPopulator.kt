package co.edu.udea.compumovil.labs20211_gr03.lab2.populator

import co.edu.udea.compumovil.labs20211_gr03.lab2.databinding.FragmentCrearSitioBinding
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI

class SitioTuristicoPopulator {

    companion object {
        fun populate(sitio: POI, binding: FragmentCrearSitioBinding){
            sitio.nombreSitio = binding.inputNombreSitio.text.toString()
            sitio.informacion = binding.infoGeneral.text.toString()
            sitio.ubicacion = binding.inputUbicacion.text.toString()
            sitio.temperatura = binding.inputTemperatura.text.toString()
            sitio.actividades = binding.inputSitiosRecomendados.text.toString()
            sitio.puntuacion = binding.inputPuntuacion.text.toString()
            // TODO: falta la imagen
        }
    }
}
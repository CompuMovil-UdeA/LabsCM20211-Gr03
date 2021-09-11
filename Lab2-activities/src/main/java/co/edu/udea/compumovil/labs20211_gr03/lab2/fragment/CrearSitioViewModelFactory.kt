package co.edu.udea.compumovil.labs20211_gr03.lab2.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.POIDao

class CrearSitioViewModelFactory(private val poiDao: POIDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrearSitioViewModel::class.java)) {
            return CrearSitioViewModel(poiDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
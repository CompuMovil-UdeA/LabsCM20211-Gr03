package co.edu.udea.compumovil.labs20211_gr03.lab2.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.POIDao
import javax.sql.CommonDataSource

class ListaSitiosViewModelFactory(private val dataSource: POIDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ListaSitiosViewModel::class.java)) {
                return ListaSitiosViewModel(
                    dataSource
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

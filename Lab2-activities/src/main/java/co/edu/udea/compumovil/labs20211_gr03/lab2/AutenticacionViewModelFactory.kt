package co.edu.udea.compumovil.labs20211_gr03.lab2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.UserDao

class AutenticacionViewModelFactory(private val database: UserDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AutenticacionViewModel::class.java)) {
            return AutenticacionViewModel(
                database
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
package co.edu.udea.compumovil.labs20211_gr03.lab2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.UserDao

class RegistroUsuarioViewModelFactory (private val database : UserDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistroUsuarioViewModel::class.java)) {
            return RegistroUsuarioViewModel(
                    database
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
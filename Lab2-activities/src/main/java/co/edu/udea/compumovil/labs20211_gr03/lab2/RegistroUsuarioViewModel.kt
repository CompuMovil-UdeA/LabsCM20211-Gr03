package co.edu.udea.compumovil.labs20211_gr03.lab2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.UserDao
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.User
import kotlinx.coroutines.*

class RegistroUsuarioViewModel (private val database : UserDao) : ViewModel() {
    private val viewModelJob = Job()
    //creo el hilo principal
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    //variable LiveData
    private var _navigateToLogin =  MutableLiveData<Boolean>()
    val navigateToLogin : LiveData<Boolean>
        get() = _navigateToLogin

    init {
        _navigateToLogin.value = false
    }

    fun onNavigateToLoginSuccess(){
        _navigateToLogin.value = false
    }

    fun insert(user: User){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.insert(user)
            }
        }
        _navigateToLogin.value = true
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
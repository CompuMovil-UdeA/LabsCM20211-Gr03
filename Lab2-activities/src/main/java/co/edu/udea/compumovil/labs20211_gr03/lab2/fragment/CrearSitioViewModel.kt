package co.edu.udea.compumovil.labs20211_gr03.lab2.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.POIDao
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI
import kotlinx.coroutines.*

class CrearSitioViewModel(private val poiDao: POIDao): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private var _navigateToRequestsFragment =  MutableLiveData<Boolean>()
    val navigateToRequestsFragment : LiveData<Boolean>
        get() = _navigateToRequestsFragment

    fun doneNavigation(){
        _navigateToRequestsFragment.value = false
    }

    fun insert(sitio: POI){
        uiScope.launch {
            withContext(Dispatchers.IO){
                poiDao.insert(sitio)
            }
        }
        _navigateToRequestsFragment.value = true
    }

    fun update(sitio: POI){
        uiScope.launch {
            withContext(Dispatchers.IO){
                poiDao.update(sitio)
            }
        }
        _navigateToRequestsFragment.value = true
    }

    override fun onCleared() {
        viewModelJob.cancel()
        super.onCleared()
    }
}
package co.edu.udea.compumovil.labs20211_gr03.lab2.fragment

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.POIDao
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI
import kotlinx.coroutines.*

class ListaSitiosViewModel(private val database : POIDao): ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val sitios = database.getAll()

   fun delete(sitioturistico:POI){
        uiScope.launch {
            withContext(Dispatchers.IO){
                database.delete(sitioturistico.sitioid)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
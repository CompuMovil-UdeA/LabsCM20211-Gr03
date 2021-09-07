package co.edu.udea.compumovil.labs20211_gr03.lab2.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI

@Dao
interface POIDao {
    @Insert
    fun insert(request: POI)

    @Query(value = "SELECT * from tabla_POI ORDER BY sitioid DESC")
    fun getAll(): LiveData<List<POI>>

    @Update
    fun update(request: POI)


    // @ToDO getByID

    @Query(value = "DELETE from tabla_POI where sitioid = :key")
    fun delete(key:Int)

}
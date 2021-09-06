package co.edu.udea.compumovil.labs20211_gr03.lab2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.POIDao
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.UserDao
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.User

@Database(entities = arrayOf(User::class, POI::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun poiDao(): POIDao
}
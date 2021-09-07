package co.edu.udea.compumovil.labs20211_gr03.lab2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.POIDao
import co.edu.udea.compumovil.labs20211_gr03.lab2.dao.UserDao
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.POI
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.User

@Database(entities = arrayOf(User::class, POI::class), version = 1, exportSchema = false)
abstract class SitiosTuristicosDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun poiDao(): POIDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SitiosTuristicosDatabase? = null

        fun getDatabase(context: Context): SitiosTuristicosDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SitiosTuristicosDatabase::class.java,
                        "sitiosturisticos_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}
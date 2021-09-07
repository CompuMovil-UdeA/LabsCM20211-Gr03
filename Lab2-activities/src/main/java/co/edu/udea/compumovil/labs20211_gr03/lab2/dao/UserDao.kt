package co.edu.udea.compumovil.labs20211_gr03.lab2.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import co.edu.udea.compumovil.labs20211_gr03.lab2.models.User

@Dao
interface UserDao {

    @Query(value = "SELECT * from tabla_usuario where usuario = :usuario AND contraseña = :contraseña LIMIT 1")
    fun authenticate(usuario : String, contraseña : String) : User?

    @Query(value = "SELECT * from tabla_usuario where uid = :userId LIMIT 1")
    fun getUser(userId : Int) : User?

    @Insert
    fun insert(user: User)
}
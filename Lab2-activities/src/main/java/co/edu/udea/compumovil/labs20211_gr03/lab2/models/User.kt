package co.edu.udea.compumovil.labs20211_gr03.lab2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuario")
data class User(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "usuario") val usuario: String?,
    @ColumnInfo(name = "contraseña") val contraseña: String?,
    @ColumnInfo(name = "correo") val correo: String?
)

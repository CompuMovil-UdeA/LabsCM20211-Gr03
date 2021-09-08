package co.edu.udea.compumovil.labs20211_gr03.lab2.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField


@Entity(tableName = "tabla_usuario")
data class User(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,

        @ColumnInfo(name = "usuario")
        var usuario: String = "",

        @ColumnInfo(name = "contraseña")
        var contraseña: String = "",

        @ColumnInfo(name = "correo")
        var correo: String = ""
)
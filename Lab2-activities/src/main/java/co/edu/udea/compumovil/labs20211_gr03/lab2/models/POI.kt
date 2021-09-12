package co.edu.udea.compumovil.labs20211_gr03.lab2.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabla_POI")
data class POI(
    @PrimaryKey(autoGenerate = true)
    var sitioid: Int = 0,

    @ColumnInfo(name = "nombreSitio")
    var nombreSitio: String = "",

    @ColumnInfo(name = "informacion")
    var informacion: String = "",

    @ColumnInfo(name = "ubicacion")
    var ubicacion: String = "",

    @ColumnInfo(name = "temperatura")
    var temperatura: String = "",

    @ColumnInfo(name = "actividades")
    var actividades: String = "",

    @ColumnInfo(name = "puntuacion")
    var puntuacion: String = "",

    @ColumnInfo(name = "imagenUrl")
    var imagenUrl: String = ""
): Parcelable
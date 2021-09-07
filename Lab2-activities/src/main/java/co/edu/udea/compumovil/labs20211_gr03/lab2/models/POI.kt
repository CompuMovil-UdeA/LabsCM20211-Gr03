package co.edu.udea.compumovil.labs20211_gr03.lab2.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_POI")
data class POI(
    @PrimaryKey val sitioid: Int,
    @ColumnInfo(name = "nombreSitio") val nombreSitio: String?,
    @ColumnInfo(name = "informacion") val informacion: String?,
    @ColumnInfo(name = "ubicacion") val ubicacion: String?,
    @ColumnInfo(name = "temperatura") val temperatura: String?,
    @ColumnInfo(name = "actividades") val actividades: String?
)
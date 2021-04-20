package co.edu.udea.compumovil.labs20211_gr03.lab1.models

data class Country(
    var name:String,
    var code: String
){
    override fun toString(): String = name
}
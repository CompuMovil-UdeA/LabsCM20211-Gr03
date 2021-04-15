package co.edu.udea.compumovil.labs20211_gr03.lab1

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner

class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)

        //Spinner Grado de Escolaridad
        val spinner = findViewById<Spinner>(R.id.spinner_schoolGrade)
        var list = resources.getStringArray(R.array.array_gradoEscolaridad)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        spinner.adapter = adaptador

    }
}
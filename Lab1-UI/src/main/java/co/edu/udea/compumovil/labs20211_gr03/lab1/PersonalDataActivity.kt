package co.edu.udea.compumovil.labs20211_gr03.lab1

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import co.edu.udea.compumovil.labs20211_gr03.lab1.dialog.DatePickerFragment


class PersonalDataActivity : AppCompatActivity() {
    private lateinit var birthDate: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)

        birthDate = findViewById<EditText>(R.id.input_birthDate)
        birthDate.setOnClickListener {
            showDatePickerDialog()
        }


        //Spinner Grado de Escolaridad
        val spinner = findViewById<Spinner>(R.id.spinner_schoolGrade)
        var list = resources.getStringArray(R.array.array_gradoEscolaridad)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        spinner.adapter = adaptador


    }

    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            birthDate.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }


}
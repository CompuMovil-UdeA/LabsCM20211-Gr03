package co.edu.udea.compumovil.labs20211_gr03.lab1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import java.util.regex.Pattern


class PersonalDataActivity : AppCompatActivity() {
    private lateinit var txt_names: TextInputLayout
    private lateinit var input_names: TextInputEditText
    private lateinit var txt_lastnames: TextInputLayout
    private lateinit var input_lastnames: TextInputEditText
    private lateinit var btn_next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)

        txt_names = findViewById(R.id.txt_names)
        input_names = findViewById(R.id.input_names)
        txt_lastnames = findViewById(R.id.txt_lastnames)
        input_lastnames = findViewById(R.id.input_lastnames)
        btn_next = findViewById(R.id.btn_next_infoContact)



        //Spinner Grado de Escolaridad
        val spinner = findViewById<Spinner>(R.id.spinner_schoolGrade)
        var list = resources.getStringArray(R.array.array_gradoEscolaridad)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        spinner.adapter = adaptador


        btn_next.setOnClickListener { v ->
            val intent = Intent(this, ContactDataActivity::class.java)
            startActivity(intent)
        }

        validarCampos()
    }

    private fun validarCampos() {
        input_names.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_NEXT && input_names.text.toString() == "") {
                txt_names.error = getString(R.string.msj_error_nombre)
                true
            }else {
                txt_names.error = null
                false
            }
        }

        input_lastnames.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_NEXT && input_lastnames.text.toString() == "") {
                txt_lastnames.error = getString(R.string.msj_error_apellido)
                true
            }else {
                txt_lastnames.error = null
                false
            }
        }
    }

    //Metodo para mostrar el DataPicker
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            // Display Selected date in Toast
            Toast.makeText(this, """$dayOfMonth - ${monthOfYear + 1} - $year""", Toast.LENGTH_LONG).show()
        }, year, month, day)
        dpd.show()
    }
}
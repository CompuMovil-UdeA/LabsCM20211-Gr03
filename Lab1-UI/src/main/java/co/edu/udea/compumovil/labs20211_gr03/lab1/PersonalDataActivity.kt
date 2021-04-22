package co.edu.udea.compumovil.labs20211_gr03.lab1

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
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
    private lateinit var rb_man: RadioButton
    private lateinit var rb_women: RadioButton
    private lateinit var rg_gender: RadioGroup
    private lateinit var btn_next: Button
    private lateinit var titulo: TextView
    private lateinit var fecha_nacimiento: String
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)

        txt_names = findViewById(R.id.txt_names)
        input_names = findViewById(R.id.input_names)
        txt_lastnames = findViewById(R.id.txt_lastnames)
        input_lastnames = findViewById(R.id.input_lastnames)
        btn_next = findViewById(R.id.btn_next_infoContact)
        titulo = findViewById(R.id.txt_personalInfo)
        rb_man = findViewById(R.id.rb_man)
        rb_women = findViewById(R.id.rb_women)
        rg_gender = findViewById(R.id.rg_gender)

        fecha_nacimiento = ""

        //Spinner Grado de Escolaridad
        spinner = findViewById<Spinner>(R.id.spinner_schoolGrade)
        var list = resources.getStringArray(R.array.array_gradoEscolaridad)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        spinner.adapter = adaptador


        btn_next.setOnClickListener { v ->
            validarNombre()
            validarApellido()

            // No hay error
            if(txt_names.error == null && txt_lastnames.error == null ){
                loggearInfo()

                val intent = Intent(this, ContactDataActivity::class.java)
                startActivity(intent)
            }
        }

        validarCampos()
    }

    private fun validarCampos() {
        input_names.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_NEXT) {
                validarNombre()
                true
            }else {
                false
            }
        }

        input_lastnames.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_NEXT) {
                validarApellido()
                true
            }else {

                false
            }
        }
    }

    private fun validarNombre() {
        if(input_names.text.toString() == ""){
            txt_names.error = getString(R.string.msj_error_nombre)
        }else{
            txt_names.error = null
        }
    }

    private fun validarApellido() {
        if(input_lastnames.text.toString() == ""){
            txt_lastnames.error = getString(R.string.msj_error_apellido)
        }else{
            txt_lastnames.error = null
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
            fecha_nacimiento = """$dayOfMonth - ${monthOfYear + 1} - $year"""
            Toast.makeText(this, fecha_nacimiento, Toast.LENGTH_LONG).show()
        }, year, month, day)
        dpd.show()
    }

     private fun loggearInfo(){
         val selectedRadioButtonId: Int = rg_gender.checkedRadioButtonId
         val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
         val selectedRadioButtonValue: String = selectedRadioButton.text.toString()

         val opcional1 = if (spinner.selectedItem.toString() == getString(R.string.escolaridad)) "" else spinner.selectedItem.toString()

         val info: String =  getString(R.string.nombres) + ": " + input_names.text.toString() + "\n" +
                getString(R.string.apellidos) + ": " + input_lastnames.text.toString() + "\n" +
                getString(R.string.sexo) + ": " + selectedRadioButtonValue + "\n" +
                getString(R.string.fecha_de_nacimiento) + ": " + fecha_nacimiento + "\n" +
                getString(R.string.escolaridad) + ": " + opcional1 + "\n"

        Log.i(titulo.text.toString(), info)
    }
}
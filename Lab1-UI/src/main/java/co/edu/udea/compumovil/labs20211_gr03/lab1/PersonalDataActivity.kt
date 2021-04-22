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
    private lateinit var btn_next: Button
    private lateinit var fecha_nacimiento: String
    private lateinit var rg_genero: RadioGroup
    private lateinit var rta_genero: String
    private lateinit var spinner: Spinner
    private lateinit var btn_datapicker: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)

        txt_names = findViewById(R.id.txt_names)
        input_names = findViewById(R.id.input_names)
        txt_lastnames = findViewById(R.id.txt_lastnames)
        input_lastnames = findViewById(R.id.input_lastnames)
        btn_datapicker = findViewById(R.id.btn_showDatapicker)
        btn_next = findViewById(R.id.btn_next_infoContact)
        fecha_nacimiento= ""
        rg_genero = findViewById(R.id.rg_gender)
        rta_genero = ""

        //Spinner Grado de Escolaridad
        spinner = findViewById<Spinner>(R.id.spinner_schoolGrade)
        var list = resources.getStringArray(R.array.array_gradoEscolaridad)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
        spinner.adapter = adaptador

        val genero_por_defecto = findViewById<RadioButton>(R.id.rb_man)
        genero_por_defecto.isSelected = true




        btn_next.setOnClickListener { v ->
            validarNombre()
            validarApellido()

            // No hay error
            if(txt_names.error == null && txt_lastnames.error == null ){
                imprimirEnConsola()

                val intent = Intent(this, ContactDataActivity::class.java)
                startActivity(intent)
            }
        }

        validarCampos()
    }

    //Metodo para mostrar el DataPicker
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPicker(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                fecha_nacimiento = """$dayOfMonth - ${monthOfYear + 1} - $year"""
                Toast.makeText(this, fecha_nacimiento, Toast.LENGTH_LONG).show()


        }, year, month, day)
        dpd.show()
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

    private fun imprimirEnConsola(){
        var txt_title = findViewById<TextView>(R.id.txt_personalInfo)
        var titulo = txt_title.text.toString()
        var nombres= input_names.text.toString()
        val apellidos = input_lastnames.text.toString()
        val selectedRadioButtonId: Int = rg_genero.checkedRadioButtonId
        val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
        val rta_genero: String = selectedRadioButton.text.toString()
        val nivel_academico = spinner.selectedItem.toString()

        var datos : String

        datos = "...\n$titulo\n$nombres\n$apellidos\n$rta_genero\n$fecha_nacimiento\n$nivel_academico"


        Log.i("Seguimiento", datos )
    }


}
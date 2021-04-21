package co.edu.udea.compumovil.labs20211_gr03.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.City
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.Country
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.Region
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.regex.Pattern


class ContactDataActivity : AppCompatActivity(){
    private val API_KEY: String = "c2e42fed8dc9646e0de6a0be3c7b472b"
    private lateinit var retrofit: Retrofit
    private lateinit var service: CountryAPIService
    private lateinit var pais_menu: TextInputLayout
    private lateinit var region_menu: TextInputLayout
    private lateinit var ciudad_menu: TextInputLayout
    private lateinit var txt_phone: TextInputLayout
    private lateinit var txt_email: TextInputLayout
    private lateinit var txt_address: TextInputLayout
    private lateinit var paises_items: AutoCompleteTextView
    private lateinit var regiones_items: AutoCompleteTextView
    private lateinit var ciudades_items: AutoCompleteTextView
    private lateinit var input_phone: TextInputEditText
    private lateinit var input_email: TextInputEditText
    private lateinit var input_address: TextInputEditText
    private lateinit var btn_enviar: Button
    private lateinit var paises: List<Country>
    private lateinit var regiones: List<Region>
    private lateinit var ciudades: List<City>
    private lateinit var pais_seleccionado: Country
    private lateinit var region_seleccionado: Region
    private lateinit var ciudad_seleccionado: City



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        pais_menu = findViewById(R.id.menu_country)
        region_menu = findViewById(R.id.menu_region)
        ciudad_menu = findViewById(R.id.menu_city)
        paises_items = findViewById(R.id.items_country)
        regiones_items = findViewById(R.id.items_region)
        ciudades_items = findViewById(R.id.items_city)
        input_phone =  findViewById(R.id.input_phone)
        input_email =  findViewById(R.id.input_email)
        input_address =  findViewById(R.id.input_address)
        txt_phone = findViewById(R.id.txt_phone)
        txt_email = findViewById(R.id.txt_email)
        txt_address = findViewById(R.id.txt_address)
        btn_enviar = findViewById(R.id.btn_send)

        // Deshabilitamos los dropdown
        pais_menu.isEnabled = false
        region_menu.isEnabled = false
        ciudad_menu.isEnabled = false


        btn_enviar.setOnClickListener { v ->
            Toast.makeText(
                this,
                R.string.mensaje_enviar,
                Toast.LENGTH_LONG
            ).show()
        }


        validarCampos()

        retrofit = RestEngine.getRestEngine()
        service = retrofit.create<CountryAPIService>(CountryAPIService::class.java)
        getCountries()
    }

    private fun validarCampos() {

        input_phone.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_NEXT && input_phone.text.toString() == "") {
                txt_phone.error = getString(R.string.msj_error_telefono)
                true
            }else {
                txt_phone.error = null
                false
            }
        }

        input_email.setOnEditorActionListener() { v, actionId, event ->
            var patron: Pattern = Patterns.EMAIL_ADDRESS
            var email: String = input_email.text.toString()

            if(actionId == EditorInfo.IME_ACTION_NEXT && (email == "" || !patron.matcher(email).matches())){
                txt_email.error = getString(R.string.msj_error_email)
                true
            }else {
                txt_email.error = null
                false
            }
        }

        paises_items.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_UNSPECIFIED && (paises_items.text.toString() == getString(R.string.seleccione))){
                pais_menu.error = getString(R.string.msj_error_pais)
                true
            }else {
                pais_menu.error = null
                false
            }
        }
    }

    private fun llenarSpinnerPaises(data: List<Country>) {
        paises_items.setText(R.string.seleccione)
        var adapterTest = ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        //paises_spinner.adapter = adapterTest
        paises_items.setAdapter(adapterTest)
        pais_menu.isEnabled = true

        paises_items.setOnItemClickListener { parent, view, position, id ->
            pais_seleccionado = parent.getItemAtPosition(position) as Country
            getRegions(pais_seleccionado.code)
        }
    }

    private fun  llenarSpinerRegiones(data: List<Region>){
        regiones_items.setText(R.string.seleccione)

        if(data.isEmpty()){
            regiones_items.setText(R.string.no_encontrado)
        }else{
            var adapterTest = ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
            regiones_items.setAdapter(adapterTest)
            region_menu.isEnabled = true

            regiones_items.setOnItemClickListener { parent, view, position, id ->
                region_seleccionado = parent.getItemAtPosition(position) as Region
                getCities(region_seleccionado.country, region_seleccionado.region)
            }
        }
    }

    private fun  llenarSpinerCiudades(data: List<City>){
        ciudades_items.setText(R.string.seleccione)

        if(data.isEmpty()){
            ciudades_items.setText(R.string.no_encontrado)
        }else{
            var adapterTest = ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
            ciudades_items.setAdapter(adapterTest)
            ciudad_menu.isEnabled = true

            ciudades_items.setOnItemClickListener { parent, view, position, id ->
                ciudad_seleccionado = parent.getItemAtPosition(position) as City
            }
        }
    }

    private fun getCountries() {
        val result: Call<List<Country>> = service.getCountries(API_KEY)

        result.enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                paises = response?.body()!!
                llenarSpinnerPaises(paises)
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                t?.printStackTrace()
            }
        })
    }


    private fun getRegions(country_code: String) {
        val result: Call<List<Region>> = service.getRegions(country_code, API_KEY)

        result.enqueue(object : Callback<List<Region>> {
            override fun onResponse(call: Call<List<Region>>, response: Response<List<Region>>) {
                regiones = response?.body()!!
                llenarSpinerRegiones(regiones)
            }

            override fun onFailure(call: Call<List<Region>>, t: Throwable) {
                t?.printStackTrace()
            }
        })
    }

    private fun getCities(country_code: String, region: String) {
        val result: Call<List<City>> = service.getCities(country_code, region, API_KEY)

        result.enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                ciudades = response?.body()!!
                llenarSpinerCiudades(ciudades)
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                t?.printStackTrace()
            }
        })
    }

}
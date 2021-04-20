package co.edu.udea.compumovil.labs20211_gr03.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.City
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.Country
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.Region
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit



class ContactDataActivity : AppCompatActivity(){
    private val API_KEY: String = "c2e42fed8dc9646e0de6a0be3c7b472b"
    private lateinit var retrofit: Retrofit
    private lateinit var service: CountryAPIService
    private lateinit var pais_menu: TextInputLayout
    private lateinit var region_menu: TextInputLayout
    private lateinit var ciudad_menu: TextInputLayout
    private lateinit var paises_items: AutoCompleteTextView
    private lateinit var regiones_items: AutoCompleteTextView
    private lateinit var ciudades_items: AutoCompleteTextView
    private lateinit var paises_spinner: Spinner
    private lateinit var paises: List<Country>
    private lateinit var regiones: List<Region>
    private lateinit var ciudades: List<City>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        pais_menu = findViewById(R.id.menu_country)
        region_menu = findViewById(R.id.menu_region)
        ciudad_menu = findViewById(R.id.menu_city)
        paises_items = findViewById(R.id.items_country)
        regiones_items = findViewById(R.id.items_region)
        ciudades_items = findViewById(R.id.items_city)
        paises_spinner = findViewById(R.id.spinner_country)

        // Deshabilitamos los dropdown
        pais_menu.isEnabled = false
        region_menu.isEnabled = false
        ciudad_menu.isEnabled = false

        retrofit = RestEngine.getRestEngine()
        service = retrofit.create<CountryAPIService>(CountryAPIService::class.java)
        getCountries()
    }

    private fun llenarSpinnerPaises(data: List<Country>) {
        paises_items.setText(R.string.seleccione)
        var adapterTest = ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        paises_spinner.adapter = adapterTest
        paises_items.setAdapter(adapterTest)
        pais_menu.isEnabled = true

        paises_items.setOnItemClickListener { parent, view, position, id ->
            val pais: Country = parent.getItemAtPosition(position) as Country
            Toast.makeText(this, pais.code, Toast.LENGTH_LONG).show()

            getRegions(pais.code)
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
                val region: Region = parent.getItemAtPosition(position) as Region
                Toast.makeText(this, region.region, Toast.LENGTH_LONG).show()

                getCities(region.country, region.region)
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
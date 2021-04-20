package co.edu.udea.compumovil.labs20211_gr03.lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.Country
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit



class ContactDataActivity : AppCompatActivity() {
    private val API_KEY: String = "c2e42fed8dc9646e0de6a0be3c7b472b"
    private lateinit var retrofit: Retrofit
    private lateinit var service: CountryAPIService
    private lateinit var paises_spinner: Spinner
    private lateinit var paises: List<Country>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_data)

        retrofit = RestEngine.getRestEngine()
        service = retrofit.create<CountryAPIService>(CountryAPIService::class.java)
        getCountries()
    }

    private fun llenarSpinnerPaises(data: List<Country>) {
        var adapterTest = ArrayAdapter(this,android.R.layout.simple_list_item_1,data)
        paises_spinner = findViewById(R.id.spinner_country)
        paises_spinner.adapter = adapterTest
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


}
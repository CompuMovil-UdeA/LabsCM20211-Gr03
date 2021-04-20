package co.edu.udea.compumovil.labs20211_gr03.lab1


import com.squareup.okhttp.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestEngine {

    companion object{

        fun getRestEngine() : Retrofit {
            val client = OkHttpClient.Builder().build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://battuta.medunes.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit
        }

    }

}
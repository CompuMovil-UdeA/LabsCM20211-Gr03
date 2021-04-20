package co.edu.udea.compumovil.labs20211_gr03.lab1
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.City
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.Country
import co.edu.udea.compumovil.labs20211_gr03.lab1.models.Region
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryAPIService {
    @GET("country/all/")
    fun getCountries(@Query("key") key: String): Call<List<Country>>

    @GET("region/{country_code}/all/")
    fun getRegions(@Path("country_code") country_code: String, @Query("key") key: String): Call<List<Region>>

    @GET("city/{country_code}/search/")
    fun getCities(@Path("country_code") country_code: String,  @Query("region") region: String,
                  @Query("key") key: String): Call<List<City>>
}
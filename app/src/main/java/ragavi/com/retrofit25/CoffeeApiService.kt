package ragavi.com.retrofit25

import retrofit2.Call
import retrofit2.http.GET

interface CoffeeApiService {

    // https://api.sampleapis.com/coffee/hot
    @GET("coffee/hot")
    fun getHotCoffees(): Call<List<Coffee>>

    // https://api.sampleapis.com/coffee/iced
    @GET("coffee/iced")
    fun getIcedCoffees(): Call<List<Coffee>>
}

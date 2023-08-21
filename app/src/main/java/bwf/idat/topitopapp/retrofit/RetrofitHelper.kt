package bwf.idat.topitopapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val retrofitInstance: Retrofit = Retrofit.Builder()
        .baseUrl("https://inventory-store-production.up.railway.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val productInstance = retrofitInstance.create(ProductInstance::class.java)
}
package bwf.idat.topitopapp.retrofit

import bwf.idat.topitopapp.response.ProductListResponse
import retrofit2.http.GET

interface ProductInstance {

    @GET("producto")
    suspend fun getAllMeans(): ProductListResponse


}
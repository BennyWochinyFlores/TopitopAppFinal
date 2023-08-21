package bwf.idat.topitopapp.repository

import bwf.idat.topitopapp.model.Producto
import bwf.idat.topitopapp.retrofit.RetrofitHelper

class ProductRepository {

    suspend fun getMeals(): List<Producto>{

        val response = RetrofitHelper.productInstance.getAllMeans()
        return response.products
    }


}
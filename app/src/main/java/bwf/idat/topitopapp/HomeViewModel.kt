package bwf.idat.topitopapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bwf.idat.topitopapp.model.Producto
import bwf.idat.topitopapp.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    val products: MutableLiveData<List<Producto>> = MutableLiveData<List<Producto>>()

    val repository = ProductRepository()

    fun getMealService(){

        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getMeals()
            products.postValue(response)
        }
    }


}
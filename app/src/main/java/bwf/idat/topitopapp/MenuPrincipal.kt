package bwf.idat.topitopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import bwf.idat.topitopapp.databinding.ActivityMenuPrincipalBinding
import bwf.idat.topitopapp.databinding.ItemProductoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MenuPrincipal : AppCompatActivity() {
     private lateinit var binding: ActivityMenuPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navView = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_principal)as NavHostFragment
        val navController =navView.navController
        binding.navView.setupWithNavController(navController)


       //retrofitTraer.enqueue(object :Callback<Producto> {
       //    override fun onResponse(call: Call<Producto>, response: Response<Producto>) {
       //        binding.navHostFragmentActivityPrincipal= response.body().toString()
       //    }

       //    override fun onFailure(call: Call<Producto>, t: Throwable) {
       //        Toast.makeText(this@MenuPrincipal, "No se cargo", Toast.LENGTH_SHORT).show()
       //    }

       //})





    }
}
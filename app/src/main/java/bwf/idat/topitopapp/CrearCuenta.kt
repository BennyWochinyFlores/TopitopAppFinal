package bwf.idat.topitopapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bwf.idat.topitop.CuentaFragment
import bwf.idat.topitopapp.databinding.ActivityCrearCuentaBinding
import bwf.idat.topitopapp.model.CrearUsuario
import bwf.idat.topitopapp.retrofit.CrearCuentaInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CrearCuenta : AppCompatActivity() {

    private lateinit var binding: ActivityCrearCuentaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnvolverlogin.setOnClickListener {
            val intent =Intent(this@CrearCuenta,MenuLogin::class.java)
            startActivity(intent)
            finish()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://inventory-store-production.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(CrearCuentaInterface::class.java)

        binding.btncrearcuenta.setOnClickListener {
        val nombre= binding.txtnombreregistro.text.toString()
        val apellidos=binding.txtapellidoregistro.text.toString()
        val dni=binding.txtdni.text.toString().toInt()
        val username =binding.txtusuario.text.toString()
        val password =binding.txtcontraseAregistro.text.toString()
            val bundle = Bundle().apply {
                putString("nombre", nombre)
                putString("apellidos", apellidos)
                putInt("dni", dni)
                putString("username", username)
                putString("password", password)
            }

            val fragment = CuentaFragment()
            fragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.navigation_cuenta, fragment)
                .commit()


        val call = apiService.registroUsuario(nombre,apellidos,dni,username, password)
            val intent =Intent(this@CrearCuenta,MenuLogin::class.java)
            startActivity(intent)
            finish()
            showToast("Usuario creado exitosamente")
        call.enqueue(object : Callback<CrearUsuario> {
            override fun onResponse(call: Call<CrearUsuario>, response: Response<CrearUsuario>) {
                println(response)
                if (response.isSuccessful) {
                    showToast("Usuario creado exitosamente")

                } else {
                    showToast("Usuario no creado")
                }
            }
            override fun onFailure(call: Call<CrearUsuario>, t: Throwable) {
            }
        })
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
package bwf.idat.topitop

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import bwf.idat.topitopapp.MenuLogin
import bwf.idat.topitopapp.MenuPrincipal
import bwf.idat.topitopapp.R
import bwf.idat.topitopapp.databinding.FragmentCuentaBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class CuentaFragment : Fragment() {
    private lateinit var binding: FragmentCuentaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCuentaBinding.inflate(inflater,container,false)
        val rootView = binding.root
        val arguments=arguments
        if (arguments != null){
        val codUsuario = arguments.getString("cod_usuario")
            binding.txtdnicuenta.text=codUsuario

        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)


        binding.btnDetAgregar.setOnClickListener {

            findNavController().navigate(R.id.action_navigation_cuenta_to_navigation_menu)
        }
        binding.btncerrarsesion.setOnClickListener {
            val editor = sharedPref.edit()
            editor.clear()
            editor.apply()

            findNavController().navigate(R.id.action_navigation_cuenta_to_menuLogin)
        }
    }
    private fun datos(username: String,nombre:String,apellidos:String,dni:Int){

        //val url = "https://inventory-store-production.up.railway.app/persona/${cod_persona}"

        val formBody = FormBody.Builder()
            .add("username", username)
            .add("nombre", nombre)
            .add("apellidos", apellidos)
            .add("dni", dni.toString())
            .build()

        val request = Request.Builder()
            //.url(url)
            .post(formBody)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            @SuppressLint("ResourceType")
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.charStream()?.readText()

                val jsonResponse = JSONObject(responseBody)


                println(responseBody)


            }
        })
    }
}

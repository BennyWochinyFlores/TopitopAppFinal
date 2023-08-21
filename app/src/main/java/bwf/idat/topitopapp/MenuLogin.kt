package bwf.idat.topitopapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import bwf.idat.topitop.CuentaFragment
import bwf.idat.topitopapp.databinding.ActivityMenuLoginBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MenuLogin : AppCompatActivity() {
    private lateinit var binding: ActivityMenuLoginBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getSharedPreferences("MySharedPrefs", Context.MODE_PRIVATE)

        val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

        if (isLoggedIn) {
            val intent = Intent(this@MenuLogin, MenuPrincipal::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnregistrar.setOnClickListener {
            val intent = Intent(this@MenuLogin, CrearCuenta::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnlogin.setOnClickListener {
            val username = binding.txtnombre.text.toString()
            val password = binding.txtpassword.text.toString()
            login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        val url = "https://inventory-store-production.up.railway.app/login/login"

        val formBody = FormBody.Builder()
            .add("username", username)
            .add("password", password)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    showToast("Error en la conexión")
                }
            }

            @SuppressLint("ResourceType")
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.charStream()?.readText()

                val jsonResponse = JSONObject(responseBody)
                val result = jsonResponse.optBoolean("result", false)

                if (result) {
                    val dataArray = jsonResponse.optJSONArray("data")
                    if (dataArray != null && dataArray.length() > 0) {
                        val userData = dataArray.getJSONObject(0)
                        val username = userData.optString("username")
                        val password = userData.optString("password")

                        val editor = sharedPref.edit()
                        editor.putString("username", username)
                        editor.putString("password", password)
                        editor.putBoolean("isLoggedIn", true)
                        editor.apply()

                        val intent = Intent(this@MenuLogin, MenuPrincipal::class.java)
                        startActivity(intent)
                        finish()
                        runOnUiThread {
                            showToast("Bienvenido a Topitop ${username}")
                        }
                    }
                } else {
                    runOnUiThread {
                        showToast("Usuario no Encontrado")
                    }
                }
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}


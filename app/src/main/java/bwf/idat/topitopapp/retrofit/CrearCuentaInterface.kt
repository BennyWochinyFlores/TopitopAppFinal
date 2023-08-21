package bwf.idat.topitopapp.retrofit

import bwf.idat.topitopapp.model.CrearUsuario
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CrearCuentaInterface {
        @FormUrlEncoded
        @POST("login/registro/")
        fun registroUsuario(
            @Field("nombre") nombre:String,
            @Field("apellidos") apellidos:String,
            @Field("dni") dni:Int,
            @Field("username") username: String,
            @Field("password") password: String
        ): Call<CrearUsuario>

}
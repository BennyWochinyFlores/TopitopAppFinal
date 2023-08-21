package bwf.idat.topitopapp.model

data class CrearUsuario(
    val nombre: String,
    val apellidos: String,
    val dni : Int,
    val password: String,
    val username: String
)
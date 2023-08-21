package bwf.idat.topitopapp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productos")
data class Producto(

    @PrimaryKey
    @SerializedName("cod_producto")
    val idMeal: String,

    @SerializedName("nombre")
    val name:String,

    @SerializedName("precio")
    val precio:Double = 0.0,

    @SerializedName("descripcion")
    val descripcion:String,

    @SerializedName("url_img")
    val imgUrl: String = "",

    var isFavorite: Boolean = false,

    var isAddCart: Boolean = false





):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMeal)
        parcel.writeString(name)
        parcel.writeDouble(precio)
        parcel.writeString(descripcion)
        parcel.writeString(imgUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Producto> {
        override fun createFromParcel(parcel: Parcel): Producto {
            return Producto(parcel)
        }

        override fun newArray(size: Int): Array<Producto?> {
            return arrayOfNulls(size)
        }
    }
}

fun getData(): List<Producto> = listOf(
    Producto("Polo Blanco para Hombre en cuello Circular","A" ,12.0,"ABC"),


)
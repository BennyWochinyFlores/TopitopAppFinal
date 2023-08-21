package bwf.idat.topitopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import bwf.idat.topitopapp.model.Producto
import coil.load

class ProductDeatilsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_deatils)

        val producto=intent.getParcelableExtra<Producto>("producto")
        if(producto!=null){
            val nomb:TextView=findViewById(R.id.tvDetNomb)
            val img:ImageView=findViewById(R.id.ivDetProd)
            val detalle:TextView=findViewById(R.id.txtnombrecuenta)
            val precio:TextView=findViewById(R.id.tvDetPrice)

            nomb.text=producto.name
            img.load(producto.imgUrl)
            detalle.text=producto.descripcion
            precio.text="S/ "+producto.precio
        }
    }
}
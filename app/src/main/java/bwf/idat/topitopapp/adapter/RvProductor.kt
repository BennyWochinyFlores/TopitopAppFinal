package bwf.idat.topitopapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import bwf.idat.topitopapp.databinding.ItemProductoBinding
import bwf.idat.topitopapp.model.Producto
import bwf.idat.topitopapp.utils.DatabaseUtils
import coil.load
import androidx.lifecycle.lifecycleScope
import bwf.idat.topitopapp.R
import kotlinx.coroutines.launch

class RVProductoAdapter(var productos: List<Producto>,private val lifecycleScope: LifecycleCoroutineScope): RecyclerView.Adapter<ProductVH>() {

    var onItemClick:((Producto)->Unit)? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {

        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ProductVH(binding)
    }

    override fun getItemCount(): Int {

        return productos.size
    }

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val productox=productos[position]
        holder.bind(productos[position], lifecycleScope)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(productox)

        }
    }
}


class ProductVH(private val binding : ItemProductoBinding): RecyclerView.ViewHolder(binding.root){

    @SuppressLint("SuspiciousIndentation")
    fun bind(producto: Producto, lifecycleScope: LifecycleCoroutineScope) {


        binding.txtproducto.text = producto.name
        binding.txtprecio.text ="S/ "+ producto.precio.toString()
        binding.productImageView.load(producto.imgUrl)

        if(producto.isFavorite){
            binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24_red)

        }
        else{
            binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)

        }

        binding.favoriteButton.setOnClickListener{


            val room = DatabaseUtils.getDatabase()

            lifecycleScope.launch {
                // Marcar el producto como favorito en la base de datos

                var productId = room.productDao().getProductById(producto.idMeal)
                println(productId.isFavorite)
                if(productId.isFavorite){
                    productId.isFavorite = false
                        binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)
             }
                else{
                    productId.isFavorite = true
                    binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24_red)


                }
                room.productDao().actualizarProducto(productId)
            }


        }
        binding.addToCartButton.setOnClickListener{


            val room = DatabaseUtils.getDatabase()

            lifecycleScope.launch {
                // Marcar el producto como favorito en la base de datos

                var productId = room.productDao().getProductById(producto.idMeal)

                productId.isAddCart = true



                room.productDao().actualizarProducto(productId)
            }


        }
    }

}
/*
class RvProductor(private val productos:List<Producto>): RecyclerView.Adapter<ProductoRv>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoRv {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductoRv(binding)
    }

    override fun getItemCount(): Int =productos.size

    override fun onBindViewHolder(holder: ProductoRv, position: Int) {
        holder.bind(productos[position])
    }
}
class ProductoRv(private val binding: ItemProductoBinding ):RecyclerView.ViewHolder(binding.root){
    fun bind(producto: Producto){
        binding.txtproducto.text= producto.nombre
        binding.txtprecio.text= producto.precio.toString()+"/$"
        binding.txtdescuento.text=producto.descuento.toString()+"/$"
        binding.txtprecio.paintFlags=Paint.STRIKE_THRU_TEXT_FLAG

    }

}*/
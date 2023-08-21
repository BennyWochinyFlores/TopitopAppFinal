package bwf.idat.topitopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import bwf.idat.topitopapp.R
import bwf.idat.topitopapp.databinding.ItemProductoBinding
import bwf.idat.topitopapp.model.Producto
import bwf.idat.topitopapp.utils.DatabaseUtils
import coil.load
import kotlinx.coroutines.launch

class RvFavoritosAdapter(var productos: MutableList<Producto>,private val lifecycleScope: LifecycleCoroutineScope): RecyclerView.Adapter<ProductViewHolder>(){
    var onItemClick:((Producto)->Unit)? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ProductViewHolder(binding)    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productox=productos[position]
        holder.bind(productox,lifecycleScope,this)
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(productox)
        }
    }

    fun removeProductAtPosition(position: Int) {
        productos.removeAt(position)
        notifyItemRemoved(position)
    }

}

class ProductViewHolder(private val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Producto, lifecycleScope: LifecycleCoroutineScope, adapter: RvFavoritosAdapter) {
        binding.txtproducto.text = product.name
        binding.txtprecio.text ="S/ "+ product.precio.toString()
        binding.productImageView.load(product.imgUrl)

        if(product.isFavorite){
            binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24_red)

        }
        else{
            binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)

        }

        binding.favoriteButton.setOnClickListener{


            val room = DatabaseUtils.getDatabase()

            lifecycleScope.launch {
                // Marcar el producto como favorito en la base de datos

                var productId = room.productDao().getProductById(product.idMeal)
                println(productId.isFavorite)
                if(productId.isFavorite){
                    productId.isFavorite = false
                    binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24)

                    val position = adapter.productos.indexOf(product)
                    if (position != -1) {
                        adapter.removeProductAtPosition(position)
                    }

                    adapter.notifyDataSetChanged()

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

                var productId = room.productDao().getProductById(product.idMeal)

                productId.isAddCart = true



                room.productDao().actualizarProducto(productId)
            }

        }
    }
    
}
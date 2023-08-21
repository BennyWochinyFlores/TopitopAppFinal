package bwf.idat.topitopapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import bwf.idat.topitopapp.R
import bwf.idat.topitopapp.databinding.ItemCarritoBinding
import bwf.idat.topitopapp.model.Producto
import bwf.idat.topitopapp.utils.DatabaseUtils
import coil.load
import kotlinx.coroutines.launch

class RvCartAdapter(var productos: MutableList<Producto>,private val lifecycleScope: LifecycleCoroutineScope): RecyclerView.Adapter<CartViewHolder>(){
    var onItemClick:((Producto)->Unit)? =null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCarritoBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return CartViewHolder(binding)    }

    override fun getItemCount(): Int {
        return productos.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
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

class CartViewHolder(private val binding: ItemCarritoBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: Producto, lifecycleScope: LifecycleCoroutineScope, adapter: RvCartAdapter) {
        binding.txtproducto.text = product.name
        binding.txtprecio.text ="S/ "+ product.precio.toString()
        binding.productImageView.load(product.imgUrl)

        if(product.isFavorite){

        }
        else{

        }


    }
}
package bwf.idat.topitopapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import bwf.idat.topitopapp.adapter.RvFavoritosAdapter
import bwf.idat.topitopapp.databinding.FragmentFavoritoBinding
import bwf.idat.topitopapp.model.Producto
import bwf.idat.topitopapp.room.AppDatabase
import bwf.idat.topitopapp.utils.DatabaseUtils
import kotlinx.coroutines.launch


class FavoritoFragment : Fragment() {
    private lateinit var binding: FragmentFavoritoBinding
    private lateinit var adapter: RvFavoritosAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFavoritoBinding.inflate(inflater,container,false)

        val room = DatabaseUtils.getDatabase()
        val productosfavoritos = mutableListOf<Producto>()
        lifecycleScope.launch {
            var productos = room.productDao().getAllProducts()

            for (item in productos){
                if(item.isFavorite){
                    productosfavoritos.add(item)
                }
            }

            adapter = RvFavoritosAdapter(productosfavoritos,lifecycleScope)
            binding.rcvFavorito.adapter = adapter

            // Configura el layout manager, por ejemplo:
            binding.rcvFavorito.layoutManager = LinearLayoutManager(requireContext())

            adapter.onItemClick={

                val intent= Intent(context, ProductDeatilsActivity::class.java)
                intent.putExtra("producto",it)
                startActivity(intent)
            }


        }





        println("holas")

        return binding.root
}
}
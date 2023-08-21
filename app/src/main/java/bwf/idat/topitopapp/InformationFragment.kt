package bwf.idat.topitop

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import bwf.idat.topitopapp.ProductDeatilsActivity
import bwf.idat.topitopapp.adapter.RvCartAdapter
import bwf.idat.topitopapp.adapter.RvFavoritosAdapter
import bwf.idat.topitopapp.databinding.FragmentInformacionBinding
import bwf.idat.topitopapp.model.Producto
import bwf.idat.topitopapp.utils.DatabaseUtils
import kotlinx.coroutines.launch

class InformationFragment : Fragment() {
    private lateinit var binding: FragmentInformacionBinding
    private lateinit var adapter: RvCartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentInformacionBinding.inflate(inflater,container,false)

        val room = DatabaseUtils.getDatabase()
        val productoAñadidos = mutableListOf<Producto>()
        lifecycleScope.launch {
            var productos = room.productDao().getAllProducts()

            for (item in productos){
                if(item.isAddCart){
                    productoAñadidos.add(item)
                }
            }

            adapter = RvCartAdapter(productoAñadidos,lifecycleScope)
            binding.rvcarrito.adapter = adapter

            // Configura el layout manager, por ejemplo:
            binding.rvcarrito.layoutManager = LinearLayoutManager(requireContext())




        }

        return binding.root
    }


}
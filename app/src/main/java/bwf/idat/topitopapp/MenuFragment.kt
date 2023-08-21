package bwf.idat.topitopapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bwf.idat.topitopapp.adapter.RVProductoAdapter

import bwf.idat.topitopapp.databinding.FragmentMenuBinding
import bwf.idat.topitopapp.databinding.ItemProductoBinding
import bwf.idat.topitopapp.utils.DatabaseUtils
import kotlinx.coroutines.launch

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding
    private lateinit var bindingProducto: ItemProductoBinding

    private lateinit var viewModel: HomeViewModel
    var contador = 0
    var isDatabaseInitialized = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMenuBinding.inflate(inflater,container,false)
        DatabaseUtils.initDatabase(requireContext())

        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RVProductoAdapter(listOf(),viewLifecycleOwner.lifecycleScope);

        binding.rbProductos.adapter = adapter
        binding.rbProductos.layoutManager = GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)



        viewModel.products.observe(viewLifecycleOwner){product ->


            val room = DatabaseUtils.getDatabase()

            contador++
            println("me ejecute "+ contador + "vez")

            lifecycleScope.launch {


                for (item in product) {

                    println(product.size)
                    val existingProduct = room.productDao().getProductById(item.idMeal)
                    println(existingProduct)
                    println("recorriendo...")
                    println(contador)

                    if (existingProduct == null) {

                            room.productDao().agregarUsuario(item)


                    }


                }


                adapter.productos = room.productDao().getAllProducts()
                adapter.notifyDataSetChanged()


            }



        }
        adapter.onItemClick={


            println("hola timy 123")
            val intent=Intent(context, ProductDeatilsActivity::class.java)
            intent.putExtra("producto",it)
            startActivity(intent)
        }

        viewModel.getMealService()
    }

}
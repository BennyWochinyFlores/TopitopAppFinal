package bwf.idat.topitop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bwf.idat.topitopapp.databinding.FragmentProductDeatilsBinding


class ProductDeatilsFragment : Fragment() {
    private lateinit var binding: FragmentProductDeatilsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentProductDeatilsBinding.inflate(layoutInflater,container,false)
        return binding.root

    }


}
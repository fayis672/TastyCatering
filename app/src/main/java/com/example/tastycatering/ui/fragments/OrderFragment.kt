package com.example.tastycatering.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycatering.adapter.AddressReAdapter
import com.example.tastycatering.databinding.FragmentOrderBinding
import com.example.tastycatering.ui.activity.OrderActivityArgs
import com.example.tastycatering.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_order.*
@AndroidEntryPoint
class OrderFragment : Fragment() {

    private val viewmodel: OrderViewModel by viewModels()
    private val args:OrderActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = FragmentOrderBinding.inflate(inflater,container,false)
        v.lifecycleOwner = viewLifecycleOwner
        v.vmodel = viewmodel
        return v.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewmodel.getFoodData(args.foodId)
        viewmodel.getAddress()
        viewmodel.errorGetAddress.observe(viewLifecycleOwner, Observer { error->
            if (!error){
                viewmodel.addressList.observe(viewLifecycleOwner, Observer {addressList->
                    re_address.also {
                        val layoutManager = LinearLayoutManager(requireContext())
                        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                        it.layoutManager = layoutManager
                        it.setHasFixedSize(true)
                        it.adapter = AddressReAdapter(addressList)
                    }
                })

            }
        })

    }

    companion object {

    }
}
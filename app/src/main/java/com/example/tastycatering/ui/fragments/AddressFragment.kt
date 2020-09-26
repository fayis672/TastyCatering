package com.example.tastycatering.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.example.tastycatering.R
import com.example.tastycatering.databinding.FragmentAddressBinding
import com.example.tastycatering.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_address.*

@AndroidEntryPoint
class AddressFragment : Fragment() {

    private val viewModel:OrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = FragmentAddressBinding.inflate(inflater,container,false)
        v.lifecycleOwner = viewLifecycleOwner
        v.vmodel = viewModel
        val districts = listOf("Malappuram","Kozhikod","Palakkad")
        val state = listOf("kerala","Tamilnadu")
        val disAdapter = ArrayAdapter(requireContext(),R.layout.list_item,districts)
        val stateAdapter = ArrayAdapter(requireContext(),R.layout.list_item,state)

        (v.edDistrict.editText as? AutoCompleteTextView)?.setAdapter(disAdapter)
        (v.edState.editText as? AutoCompleteTextView)?.setAdapter(stateAdapter)
        return v.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


    companion object {

    }

}
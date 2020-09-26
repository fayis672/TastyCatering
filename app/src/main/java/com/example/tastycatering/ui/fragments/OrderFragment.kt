package com.example.tastycatering.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycatering.R
import com.example.tastycatering.adapter.AddressReAdapter
import com.example.tastycatering.databinding.FragmentOrderBinding
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = FragmentOrderBinding.inflate(inflater,container,false)
        v.lifecycleOwner = viewLifecycleOwner
        return v.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        re_address.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
            it.adapter = AddressReAdapter()
        }
    }

    companion object {

    }
}
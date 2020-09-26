package com.example.tastycatering.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tastycatering.R
import com.example.tastycatering.databinding.CardAddAddressBinding
import com.example.tastycatering.databinding.CardAddressBinding
import com.example.tastycatering.ui.fragments.OrderFragmentDirections

class AddressReAdapter() : RecyclerView.Adapter<AddressReAdapter.AddressCardHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressCardHolder {

        return AddressCardHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.card_address,parent,false)
        )
    }

    override fun onBindViewHolder(holder: AddressCardHolder, position: Int) {

        holder.addressBinding.addressCard.setOnClickListener {
            val dir = OrderFragmentDirections.actionOrderFragmentToAddressFragment()
            val nav = Navigation.findNavController(holder.addressBinding.root)
            nav.navigate(dir)
        }
        
    }
    override fun getItemCount(): Int {
         return 3
    }

    class AddressCardHolder(val addressBinding: CardAddressBinding) : RecyclerView.ViewHolder(addressBinding.root)
    class AddAddressCardHolder(val cardAddAddressBinding: CardAddAddressBinding):RecyclerView.ViewHolder(cardAddAddressBinding.root)
}
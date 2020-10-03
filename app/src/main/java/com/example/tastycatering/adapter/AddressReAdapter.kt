package com.example.tastycatering.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tastycatering.R
import com.example.tastycatering.databinding.CardAddAddressBinding
import com.example.tastycatering.databinding.CardAddressBinding
import com.example.tastycatering.ui.fragments.OrderFragmentDirections

class AddressReAdapter(private val addressList:List<com.example.tastycatering.data.model.Address>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object{
        const val ADDRESS_VIEW = 0
        const val ADDRESS_ADD_VIEW = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

       return when(viewType){
            ADDRESS_VIEW->
                AddressCardHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.card_address,parent,false)
                )
            else->
                AddAddressCardHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.card_add_address,parent,false
                    )
                )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position)== ADDRESS_VIEW){

            (holder as AddressCardHolder)

                holder.addressBinding.address =
                    addressList[position].house_name+" "+
                            addressList[position].local_area+" "+
                            addressList[position].city_name+" "+
                            addressList[position].district+" "+
                            addressList[position].state+" "+
                            addressList[position].mobile_no+" "

        }else{

        (holder as AddAddressCardHolder)
        holder.cardAddAddressBinding.addAddressCard.setOnClickListener {
            val dir = OrderFragmentDirections.actionOrderFragmentToAddressFragment()
            val nav = Navigation.findNavController(holder.cardAddAddressBinding.root)
            nav.navigate(dir)
        }
        }

    }
    override fun getItemCount(): Int {
         return addressList.size+1
    }

    override fun getItemViewType(position: Int): Int {

        return when(position){
            addressList.size -> ADDRESS_ADD_VIEW
            else-> ADDRESS_VIEW
        }
    }

    class AddressCardHolder(val addressBinding: CardAddressBinding) : RecyclerView.ViewHolder(addressBinding.root)
    class AddAddressCardHolder(val cardAddAddressBinding: CardAddAddressBinding):RecyclerView.ViewHolder(cardAddAddressBinding.root)
}
package com.example.tastycatering.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tastycatering.R
import com.example.tastycatering.data.model.Order
import com.example.tastycatering.databinding.CardOrderBinding

class OrderReAdapter(
    private val orderList:List<Order>
) : RecyclerView.Adapter<OrderReAdapter.OdderHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdderHolder {
        return OdderHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.card_order,parent,false)
        )
    }

    override fun onBindViewHolder(holder: OdderHolder, position: Int) {
        holder.binding.order = orderList[position]
    }

    override fun getItemCount(): Int = orderList.size

    inner class OdderHolder(val binding:CardOrderBinding) : RecyclerView.ViewHolder(binding.root)
}
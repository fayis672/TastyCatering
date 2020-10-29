package com.example.tastycatering.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tastycatering.R
import com.example.tastycatering.data.model.Order
import com.example.tastycatering.databinding.CardOrderBinding
import com.example.tastycatering.generated.callback.OnClickListener
import com.example.tastycatering.ui.dialogs.CancelOrderConfirmDialog
import com.example.tastycatering.ui.fragments.MyOrdersFragment
import com.example.tastycatering.util.DateFormatter

class OrderReAdapter(
    private val orderList:List<Order>,
    private val callback: (String?) -> Unit
    
) : RecyclerView.Adapter<OrderReAdapter.OdderHolder>() {

    private val dateFormatter = DateFormatter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdderHolder {
        return OdderHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.card_order,parent,false)
        )
    }

    override fun onBindViewHolder(holder: OdderHolder, position: Int) {
        holder.binding.order = orderList[position]
        holder.binding.date = dateFormatter.formatDate(
            orderList[position].date_time?.year,
            orderList[position].date_time?.month,
            orderList[position].date_time?.day
        )
        holder.binding.time = dateFormatter.formatTime(
            orderList[position].date_time?.hour,
            orderList[position].date_time?.minute
        )
        holder.binding.btnCancel.setOnClickListener {
            callback.invoke(orderList[position].order_id)
        }
    }

    override fun getItemCount(): Int = orderList.size

    inner class OdderHolder(val binding:CardOrderBinding) : RecyclerView.ViewHolder(binding.root)
}
package com.example.tastycatering.util

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.example.tastycatering.adapter.AddressReAdapter
import com.example.tastycatering.databinding.CardAddressBinding

class MyItemDetailsLookUp(
    private val recyclerView: RecyclerView
) : ItemDetailsLookup<Long>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(e.x,e.y)
        return if (view!= null && recyclerView.getChildViewHolder(view).itemViewType==0){
            val holder = recyclerView.getChildViewHolder(view) as AddressReAdapter.AddressCardHolder
            return  holder.getItemDetails()
        }else
            null
}
}


package com.example.tastycatering.adapter

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AlertDialogLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.tastycatering.R
import com.example.tastycatering.model.Food
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.food_card.view.*

class FoodReAdapter(private val list: ArrayList<Food>, private val context:Context) : RecyclerView.Adapter<FoodReAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
      return Holder(LayoutInflater.from(context).inflate(R.layout.food_card,parent,false))
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
      val food = list[position]
        holder.name.text = food.name
        holder.price.text = food.price.toString()
        holder.count.text=food.count.toString()
        holder.btn_info.setOnClickListener{
            //Toast.makeText(context,"working",Toast.LENGTH_LONG).show()
           val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            //dialog.setCancelable(false)
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            dialog.setContentView(R.layout.food_details_dialog)

            val name = dialog.findViewById<MaterialTextView>(R.id.dialog_food_name)
            val disc = dialog.findViewById<MaterialTextView>(R.id.dialog_food_disc)
            val price = dialog.findViewById<MaterialTextView>(R.id.dialog_food_price)
            val btnClose = dialog.findViewById<ImageButton>(R.id.dialog_btn_close)
            name.text = food.name
            price.text = food.price.toString()
            dialog.show()
            btnClose.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: MaterialTextView = itemView.food_name
        val price: MaterialTextView = itemView.food_price
        val count: MaterialTextView = itemView.count
        val btn_plus: MaterialButton = itemView.btn_plus
        val btn_minus: MaterialButton = itemView.btn_plus
        val btn_info: ImageButton = itemView.btn_info


    }
}
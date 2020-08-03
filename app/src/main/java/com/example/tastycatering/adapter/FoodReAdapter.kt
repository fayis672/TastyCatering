package com.example.tastycatering.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.tastycatering.R
import com.example.tastycatering.data.model.Food
import com.example.tastycatering.databinding.FoodCardBinding
import com.example.tastycatering.databinding.FragmentFoodDetailsDialogBinding
import com.example.tastycatering.ui.fragments.HomeFragmentDirections


class FoodReAdapter(private val foodList: List<Food>) : RecyclerView.Adapter<FoodReAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =

        Holder(
             DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                 R.layout.food_card,parent,false)
        )

    override fun getItemCount() : Int = foodList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.foodCardBinding.food = foodList[position]
        holder.foodCardBinding.btnInfo.setOnClickListener {


            val dir = HomeFragmentDirections.actionHomeFragmentToFoodDetailsDialogFragment()
            val nav =  Navigation.findNavController(holder.foodCardBinding.root)
            nav.navigate(dir)
        }

    }

    inner class Holder(val foodCardBinding: FoodCardBinding) : RecyclerView.ViewHolder(foodCardBinding.root)

    }

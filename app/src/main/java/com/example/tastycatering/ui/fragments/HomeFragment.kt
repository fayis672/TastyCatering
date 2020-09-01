package com.example.tastycatering.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycatering.R
import com.example.tastycatering.adapter.FoodReAdapter
import com.example.tastycatering.databinding.FoodCardBinding
import com.example.tastycatering.databinding.FragmentHomeBinding
import com.example.tastycatering.viewModel.HomeViewModel
import com.google.android.material.appbar.CollapsingToolbarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.food_card.*
import kotlinx.android.synthetic.main.food_card.view.*
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    companion object{
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val v =  FragmentHomeBinding.inflate(inflater, container, false)
         v.lifecycleOwner = viewLifecycleOwner
         return v.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getFoodList()
        viewModel.foodList.observe(viewLifecycleOwner, Observer { foodList->

            if (foodList.isNotEmpty()){
                re_food.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = FoodReAdapter(foodList)
                }
            }else{
                Toast.makeText(context,"empty list",Toast.LENGTH_LONG).show()
            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collapsingLayout = view.findViewById<CollapsingToolbarLayout>(R.id.collapsing_layout)
        val navController = findNavController()
        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.tool_bar)
        val appBarConfiguration = AppBarConfiguration(navController.graph,main_drawer)
        collapsingLayout.setupWithNavController(toolbar,navController,appBarConfiguration)

    }



}
package com.example.tastycatering.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tastycatering.R
import com.example.tastycatering.databinding.FragmentFoodDetailsDialogBinding
import com.example.tastycatering.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailsDialogFragment : Fragment() {

    private val args : FoodDetailsDialogFragmentArgs by  navArgs()
    private val viewModel : HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = FragmentFoodDetailsDialogBinding.inflate(inflater, container, false)
            viewModel.getFoodList()
            viewModel.foodList.observe(viewLifecycleOwner, Observer {
                v.food = it[args.foodPosition]
            })

        return v.root
    }


    companion object {
        fun newInstance() = FoodDetailsDialogFragment()
    }

}
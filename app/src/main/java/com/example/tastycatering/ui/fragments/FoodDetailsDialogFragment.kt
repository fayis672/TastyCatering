package com.example.tastycatering.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.tastycatering.R
import com.example.tastycatering.databinding.FragmentFoodDetailsDialogBinding


class FoodDetailsDialogFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = FragmentFoodDetailsDialogBinding.inflate(inflater, container, false)
        v.lifecycleOwner = viewLifecycleOwner
        return v.root
    }

    companion object {
        fun newInstance() = FoodDetailsDialogFragment()
    }
}
package com.example.tastycatering.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycatering.adapter.OrderReAdapter
import com.example.tastycatering.databinding.FragmentMyOrdersBinding
import com.example.tastycatering.ui.dialogs.CancelOrderConfirmDialog
import com.example.tastycatering.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_my_orders.*

@AndroidEntryPoint
class MyOrdersFragment : Fragment() {

    private val viewModel:OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = FragmentMyOrdersBinding.inflate(inflater,container,false)
        v.lifecycleOwner = viewLifecycleOwner
        return v.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        re_orders.also {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.setHasFixedSize(true)
        }
        viewModel.getUserOrder()
        viewModel.orderList.observe(viewLifecycleOwner, Observer {
            if (it!=null)
            re_orders.adapter = OrderReAdapter(it) {orderId->

                if (orderId != null) {
                    val dialog = CancelOrderConfirmDialog(orderId)
                    activity?.supportFragmentManager?.let { it1 -> dialog.show(it1,"cancelOrder") }
                }

            }
            else
                Toast.makeText(requireContext(),"No orders yet",Toast.LENGTH_LONG).show()
        })


    }


    companion object{
        fun newInstance() = MyOrdersFragment()
    }

}
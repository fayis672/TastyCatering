package com.example.tastycatering.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.tastycatering.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.IllegalStateException

@AndroidEntryPoint
class CancelOrderConfirmDialog(
    private val orderID:String
) : DialogFragment() {

    private val viewmodel:OrderViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Do you want to cancel order?")
            builder.setPositiveButton(
                "Yes"
            ) { dialog, _ ->
                viewmodel.deleteOrder(orderID)
                dialog.dismiss()
                Toast.makeText(requireContext(),"Order Canceled",Toast.LENGTH_SHORT).show()
            }
            builder.setNegativeButton(
                "no"
            ) { dialog, _ ->
                dialog.dismiss()
            }
            builder.create()

        }?: throw IllegalStateException("activity cannot be null")
    }
}
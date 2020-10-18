package com.example.tastycatering.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.tastycatering.R
import com.example.tastycatering.databinding.FragmentDialogQtyBinding
import com.example.tastycatering.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QtyDialog : DialogFragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view = FragmentDialogQtyBinding.inflate(LayoutInflater.from(requireContext()))
            view.vmodel = viewModel
            builder.setView(view.root)
                .setTitle(R.string.type_quantity)
                .setPositiveButton(R.string.ok
                ) { dialog, _ ->
                    dialog.dismiss()
                    viewModel.setPrice()
                }
                .setNegativeButton(R.string.fui_cancel) { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()

        }?: throw IllegalStateException("Activity cannot be null")
    }
}
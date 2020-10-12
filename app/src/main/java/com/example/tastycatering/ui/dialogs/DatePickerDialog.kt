package com.example.tastycatering.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.tastycatering.ui.fragments.OrderFragment
import com.example.tastycatering.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ViewScoped
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class DatePickerDialog :DialogFragment(), DatePickerDialog.OnDateSetListener{

    private val viewmodel:OrderViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireContext(),this,year,month,day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        viewmodel.setDate(year,month,dayOfMonth,null,null)

    }


}
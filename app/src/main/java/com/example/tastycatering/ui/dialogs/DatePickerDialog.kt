package com.example.tastycatering.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.tastycatering.viewModel.OrderViewModel
import com.google.type.Date
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Timestamp
import java.time.LocalDate
import java.time.Year
import java.util.*

@AndroidEntryPoint
class DatePickerDialog :DialogFragment(), android.app.DatePickerDialog.OnDateSetListener{

    private val viewmodel:OrderViewModel by viewModels()

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
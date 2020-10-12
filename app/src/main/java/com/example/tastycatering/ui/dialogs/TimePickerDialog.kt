package com.example.tastycatering.ui.dialogs

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.tastycatering.data.model.Date
import com.example.tastycatering.viewModel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TimePickerDialog : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private val viewmodel:OrderViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(requireContext(),this,hour,minute,false)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

       viewmodel.setDate(null,null,null,hourOfDay,minute)

    }
}
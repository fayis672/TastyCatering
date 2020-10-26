package com.example.tastycatering.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateFormatter @Inject constructor() {

    private val c = Calendar.getInstance()

    fun formatDate(year:Int?, month:Int?, day:Int?):String?{
        return if(year!=null && month!=null && day!=null) {
            c.set(Calendar.YEAR, year)
            c.set(Calendar.MONTH, month)
            c.set(Calendar.DAY_OF_MONTH, day)
            DateFormat.getDateInstance(DateFormat.FULL).format(c.time).toString()
        }else
            null
    }

    fun formatTime(hour:Int?, minute:Int?):String?{
        return if (hour!=null && minute!=null) {
            val c = Calendar.getInstance()
            c.set(Calendar.HOUR_OF_DAY, hour)
            c.set(Calendar.MINUTE, minute)
            SimpleDateFormat("h:mm a", Locale.US).format(c.time).toString()
        }else
            null

    }
}
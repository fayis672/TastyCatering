package com.example.tastycatering.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @BindingAdapter("app:errorText")
    @JvmStatic
    fun setError(view:TextInputLayout,errMsg:String?){
        view.error=errMsg
    }

}

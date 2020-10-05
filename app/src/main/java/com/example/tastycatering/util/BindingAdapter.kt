package com.example.tastycatering.util

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputLayout

object BindingAdapter {
    @BindingAdapter("app:errorText")
    @JvmStatic
    fun setError(view:TextInputLayout,errMsg:String?){
        view.error=errMsg
    }

    @BindingAdapter("checkedChipId")
    @JvmStatic
    fun setCheckedChipId(view: ChipGroup,id: Int){

        if(id != view.checkedChipId)
        view.check(id)
    }

    @InverseBindingAdapter(attribute = "checkedChipId")
    @JvmStatic
    fun getCheckedChipId(view: ChipGroup):Int{
        return view.checkedChipId
    }

    @BindingAdapter("app:checkedChipIdAttrChanged")
    @JvmStatic
    fun setListeners(
        view: ChipGroup,
        attrChange:InverseBindingListener
        ){

            view.setOnCheckedChangeListener { group, checkedId ->
                attrChange.onChange()
            }

        }


    }



package com.example.tastycatering.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.ToggleButton
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.tastycatering.R
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_order.view.*

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

    @BindingAdapter(value = ["app:checkedChipIdAttrChanged"],requireAll = false)
    @JvmStatic
    fun setListeners(
        view: ChipGroup?,
        attrChange:InverseBindingListener?
        ){

        if (attrChange!=null){
            view?.setOnCheckedChangeListener { _, _ ->
                attrChange.onChange()
            }
        }
        }

    @BindingAdapter("app:checkIfNotNull")
    @JvmStatic
    fun setCheckIfNotNull(view: ToggleButton,value: String?){
        view.isChecked = value!=null
    }

    @BindingAdapter("app:imgUrl","app:imgError")
    @JvmStatic
    fun loadImage(view:ImageView,url:String?,errorImg:Drawable){

            Picasso.get().load(url).error(errorImg).into(view)
    }


    }



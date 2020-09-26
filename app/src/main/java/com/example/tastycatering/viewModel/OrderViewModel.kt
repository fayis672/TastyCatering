package com.example.tastycatering.viewModel

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycatering.data.model.Address
import com.example.tastycatering.data.repositry.FirebaseRepository
import com.example.tastycatering.ui.fragments.AddressFragment
import com.example.tastycatering.util.NetworkHelper
import kotlinx.coroutines.launch

class OrderViewModel @ViewModelInject constructor(
    private val networkHelper: NetworkHelper,
    private val firebaseRepository: FirebaseRepository

) : ViewModel(){

    val pincode:MutableLiveData<String> = MutableLiveData()
    val houseName:MutableLiveData<String> = MutableLiveData()
    val localArea:MutableLiveData<String> = MutableLiveData()
    val cityName:MutableLiveData<String> = MutableLiveData()
    val district:MutableLiveData<String> = MutableLiveData()
    val state:MutableLiveData<String> = MutableLiveData()
    val mobileNo:MutableLiveData<String> = MutableLiveData()
    val alterMobileNo:MutableLiveData<String> = MutableLiveData()

    fun saveAddress(){
        Log.w("hello","coming")
        viewModelScope.launch {

            if (networkHelper.isNetworkConnected()){

                firebaseRepository.addAddress(Address(
                    firebaseRepository.getUser(),
                    pincode.value,
                    houseName.value,
                    localArea.value,
                    cityName.value,
                    district.value,
                    state.value,
                    mobileNo.value,
                    alterMobileNo.value

                ))
            }
        }
    }





}
package com.example.tastycatering.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycatering.data.model.Address
import com.example.tastycatering.data.model.ErrorAddress
import com.example.tastycatering.data.model.Food
import com.example.tastycatering.data.model.Order
import com.example.tastycatering.data.repositry.FirebaseRepository
import com.example.tastycatering.util.NetworkHelper
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

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

    val selectedChip:MutableLiveData<Int> = MutableLiveData()

    val order:MutableLiveData<Order> = MutableLiveData()
    private val _errorAddress:MutableLiveData<ErrorAddress> = MutableLiveData(
        ErrorAddress(
        null,null,null,null,null,null,null,null
        ))

    val addressList:MutableLiveData<List<Address>> = MutableLiveData()
    val errorSaveAddress:MutableLiveData<Boolean> = MutableLiveData()
    val errorGetAddress:MutableLiveData<Boolean> = MutableLiveData(false)

    val food:MutableLiveData<Food> = MutableLiveData()
    val qty:MutableLiveData<Int> = MutableLiveData()

    val errorAddress:LiveData<ErrorAddress>  get() = _errorAddress

    fun saveAddress(){
        viewModelScope.launch {

            if (addressValidation()){

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

                    )).let {
                        errorSaveAddress.value = it
                    }
                }

            }

        }
    }

    fun getAddress(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){
                firebaseRepository.getAllAddress().whereEqualTo("user_id",firebaseRepository.getUser())
                    .addSnapshotListener { snapshot ,e->
                    e.let {
                        Log.e(TAG,e.toString())
                        errorGetAddress.value = true
                    }
                    snapshot.let {
                        if (it != null) {
                            addressList.value = it.toObjects(Address::class.java)
                            errorGetAddress.value = false
                        }
                    }
                }
            }
        }
    }

    fun getFoodData(foodId:String){
       viewModelScope.launch {
           if (networkHelper.isNetworkConnected()){
              firebaseRepository.getFoodData(foodId)
                  .addSnapshotListener { snapshot, e ->
                      snapshot.let {
                          if (it != null && !it.isEmpty) {
                                qty.value = it.toObjects(Food::class.java)[0].min_kg
                              food.value = it.toObjects(Food::class.java)[0]
                          }
                      }
                      e.let {
                          Log.w(TAG,it.toString())
                      }
                  }
           }
       }

    }

    fun incQty(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){
                qty.value = qty.value?.plus(1)
            }
        }
    }

    fun decQty(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){
                if (qty.value!! > food.value?.min_kg!!){
                    qty.value = qty.value?.minus(1)
                }
            }
        }


    }

    private fun addressValidation():Boolean{

        when {
            (pincode.value?:"").isEmpty() -> {
                _errorAddress.value = _errorAddress.value!!.copy(pincode = "Please enter pincode")
                return false
            }
            (pincode.value?:"").length<6 -> {
                _errorAddress.value = _errorAddress.value!!.copy(pincode = "Pincode is not valid")
                return false
            }
            else -> {
                _errorAddress.value = _errorAddress.value!!.copy(pincode = null)
            }
        }

        if ((houseName.value?:"").isEmpty()){
            _errorAddress.value = _errorAddress.value!!.copy(house_name = "Please enter house name")
            return false
        }else{
            _errorAddress.value = _errorAddress.value!!.copy(house_name = null)
        }

        if ((localArea.value?:"").isEmpty()){
            _errorAddress.value = _errorAddress.value!!.copy(local_area = "Please Local Area")
            return false
        }else{
            _errorAddress.value = _errorAddress.value!!.copy(local_area = null)
        }

        if ((cityName.value?:"").isEmpty()){
            _errorAddress.value = _errorAddress.value!!.copy(city_name = "Please enter city name")
            return false
        }else{
            _errorAddress.value = _errorAddress.value!!.copy(city_name = null)
        }

        if ((district.value?:"").isEmpty()){
            _errorAddress.value = _errorAddress.value!!.copy(district = "Please select district")
            return false
        }else{
            _errorAddress.value = _errorAddress.value!!.copy(district = null)
        }

        if ((state.value?:"").isEmpty()){
            _errorAddress.value = _errorAddress.value!!.copy(state = "Please select state")
            return false
        }else{
            _errorAddress.value = _errorAddress.value!!.copy(state = null)
        }

        when {
            (mobileNo.value?:"").isEmpty() -> {
                _errorAddress.value = _errorAddress.value!!.copy(mobile_no = "Please enter mobile no")
                return false
            }
            (mobileNo.value?:"").length<10 -> {
                _errorAddress.value = _errorAddress.value!!.copy(mobile_no = "Mobile no is not valid")
                return false
            }
            else -> {
                _errorAddress.value = _errorAddress.value!!.copy(mobile_no = null)
            }
        }

        when {
            (alterMobileNo.value?:"").isEmpty() -> {
                _errorAddress.value = _errorAddress.value!!.copy(alter_mobile_no = "Please enter Mobile no")
                return false
            }
            (mobileNo.value?:"").length<10 -> {
                _errorAddress.value = _errorAddress.value!!.copy(alter_mobile_no = "Mobile no is not valid")
                return false
            }
            else -> {
                _errorAddress.value = _errorAddress.value!!.copy(alter_mobile_no = null)
            }
        }



        return true
    }

    fun placeOrder(){
        Log.w("selected chip",selectedChip.value.toString())
    }


}
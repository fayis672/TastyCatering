package com.example.tastycatering.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tastycatering.R
import com.example.tastycatering.data.model.*
import com.example.tastycatering.data.model.Date
import com.example.tastycatering.data.repositry.FirebaseRepository
import com.example.tastycatering.util.NetworkHelper
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

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

    private val _errorAddress:MutableLiveData<ErrorAddress> = MutableLiveData(
        ErrorAddress(
        null,null,null,
            null,null,null,null,null
        ))

    val addressList:MutableLiveData<List<Address>> = MutableLiveData()
    val errorSaveAddress:MutableLiveData<Boolean> = MutableLiveData()
    val errorGetAddress:MutableLiveData<Boolean> = MutableLiveData(false)

    val food:MutableLiveData<Food> = MutableLiveData()
    val qty:MutableLiveData<String> = MutableLiveData()

    val order:MutableLiveData<Order> = MutableLiveData()
    private val date:MutableLiveData<Date> = MutableLiveData()
    val dateTxt:MutableLiveData<String> = MutableLiveData("Set Date")
    val timeTxt:MutableLiveData<String> = MutableLiveData("Set Time")
    private val selectedAddress:MutableLiveData<Address> = MutableLiveData()
    val totalPrice:MutableLiveData<Double> = MutableLiveData()
    val errorOrder:MutableLiveData<ErrorOrder> = MutableLiveData()

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
                                qty.value = it.toObjects(Food::class.java)[0].min_kg.toString()
                              food.value = it.toObjects(Food::class.java)[0]
                              setPrice()
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
                qty.value = qty.value?.toInt()?.plus(1).toString()
                setPrice()
            }
        }
    }

    fun decQty(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){
                if (qty.value!!.toInt() > food.value?.min_kg!!){
                    qty.value = qty.value?.toInt()?.minus(1).toString()
                    setPrice()
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

    fun setChip(){
        selectedChip.value = R.id.chip_kg
    }

    fun setPrice(){
        totalPrice.value = qty.value?.toDouble()?.times(food.value?.price!!)
        Log.w("food",food.value.toString())
    }

    fun setDate(year:Int?, month:Int?, day:Int?,hour:Int?, minute:Int?){

        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){
                date.value = Date(year,month,day,hour,minute)

                if(year!=null && month!=null && day!=null){
                    val c = Calendar.getInstance()
                    c.set(Calendar.YEAR,year)
                    c.set(Calendar.MONTH,month)
                    c.set(Calendar.DAY_OF_MONTH,day)
                    val dateString= DateFormat.getDateInstance(DateFormat.FULL).format(c.time).toString()
                    dateTxt.value = dateString
                }

                if (hour!=null && minute!=null){
                    val c = Calendar.getInstance()
                    c.set(Calendar.HOUR,hour)
                    c.set(Calendar.MINUTE,minute)
                    timeTxt.value = SimpleDateFormat("h:mm a", Locale.CHINA).format(c.time)

                }
            }
        }
    }

    fun setAddress(address: Address?){
       selectedAddress.value = address
        Log.w("okkkkk",address?.house_name+"got")
    }

    fun orderValidation():Boolean{
       if ((qty.value?:"").isEmpty()){
           errorOrder.value = errorOrder.value?.copy(qty_check = false,msg = "Quantity cannot be empty")
           return false
       }else
           errorOrder.value = errorOrder.value?.copy(qty_check = true,msg = null)

        if ((dateTxt.value?:"").isEmpty()){
            errorOrder.value = errorOrder.value?.copy(date_Check = false,msg = "Select Date")
            return false
        }else
            errorOrder.value = errorOrder.value?.copy(date_Check = true,msg = null)

        if ((timeTxt.value?:"").isEmpty()){
            errorOrder.value = errorOrder.value?.copy(date_Check = false,msg = "Select Time")
            return false
        }else
            errorOrder.value = errorOrder.value?.copy(date_Check = true,msg = null)

        if ((selectedAddress.value?.city_name ?:"").isEmpty()){
            errorOrder.value = errorOrder.value?.copy(qty_check = false,msg = "Quantity cannot be empty")
            return false
        }else
            errorOrder.value = errorOrder.value?.copy(qty_check = true,msg = null)

        return true
    }

    fun placeOrder(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){

                if (orderValidation()){
                    val unit = when (selectedChip.value) {
                        R.id.chip_kg -> "kg"
                        else -> "per person"
                    }

                    val orderID = UUID.randomUUID().toString()+firebaseRepository.getUser()
                    firebaseRepository.addOrder(
                        Order(
                            orderID,
                            firebaseRepository.getUser(),
                            food.value?.food_id,
                            unit,qty.value,date.value,
                            selectedAddress.value,totalPrice.value!!

                        ))
                }
            }
        }

    }

}
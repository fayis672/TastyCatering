package com.example.tastycatering.viewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tastycatering.data.model.Food
import com.example.tastycatering.data.repositry.FirebaseRepository
import com.example.tastycatering.util.NetworkHelper
import kotlinx.coroutines.launch



class HomeViewModel @ViewModelInject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val networkHelper: NetworkHelper
) :ViewModel() {

    val foodList:MutableLiveData<List<Food>> = MutableLiveData()
    val error:MutableLiveData<Boolean> = MutableLiveData()
    val mainImgUrl:MutableLiveData<String> = MutableLiveData()


    fun getFoodList(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){

                firebaseRepository.getFood().addSnapshotListener { snapshot, firestoreException ->

                    firestoreException?.let {
                        Log.e("error","fireerror")
                        error.value= true
                    }
                    snapshot?.let {
                        foodList.value= it.toObjects(Food::class.java)
                        error.value=false
                    }
                }
            }
        }
    }

    fun getImageUrl(){
        viewModelScope.launch {
            firebaseRepository.getImgUrl().addOnSuccessListener {
                mainImgUrl.value = it.toString()
                Log.w("url",it.toString())
            }
                .addOnFailureListener{
                    Log.w("errpr",it.toString())
                }
        }
    }



}




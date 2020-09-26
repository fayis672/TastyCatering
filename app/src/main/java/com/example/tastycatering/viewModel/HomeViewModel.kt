package com.example.tastycatering.viewModel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tastycatering.data.model.Food
import com.example.tastycatering.data.db.entity.SelectedFood
import com.example.tastycatering.data.repositry.FirebaseRepository
import com.example.tastycatering.data.repositry.SqLiteRepo
import com.example.tastycatering.util.NetworkHelper
import kotlinx.coroutines.launch



class HomeViewModel @ViewModelInject constructor(
    private val firebaseRepository: FirebaseRepository,
    private val sqLiteRepo: SqLiteRepo,
    private val networkHelper: NetworkHelper
) :ViewModel() {

    val foodList:MutableLiveData<List<Food>> = MutableLiveData()


    fun getFoodList(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()){

                firebaseRepository.getFood().addSnapshotListener { snapshot, firestoreException ->

                    firestoreException?.let {
                        Log.e("error","fireerror")
                    }
                    snapshot?.let {
                        foodList.value= it.toObjects(Food::class.java)
                    }
                }
            }
        }
    }

}




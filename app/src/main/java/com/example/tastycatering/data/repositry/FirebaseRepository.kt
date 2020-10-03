package com.example.tastycatering.data.repositry

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.tastycatering.data.model.Address
import com.example.tastycatering.data.model.Food
import com.example.tastycatering.data.model.Response
import com.example.tastycatering.viewModel.HomeViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseRepository @Inject constructor(


){

    private val firestore = FirebaseFirestore.getInstance()
    private val user = FirebaseAuth.getInstance().currentUser
    private val error:MutableLiveData<Boolean> = MutableLiveData(false)
    private val response:MutableLiveData<Response> = MutableLiveData(Response(null,true,null))


    fun getUser() = user!!.uid


    fun  getFood() :CollectionReference = firestore.collection("food")

    fun  addAddress(address: Address): Boolean {

        firestore.collection("address").add(address)
            .addOnSuccessListener {
                Log.d("ok","Document Written with id ${it.id}")
                error.value = false
            }
            .addOnFailureListener {
                Log.w("error",it.toString())
                error.value = true

            }
        return error.value!!
    }

    fun getAllAddress() : CollectionReference = firestore.collection("address")

    fun getFoodData(foodId:String)=
      firestore.collection("food").whereEqualTo("food_id",foodId)

    }





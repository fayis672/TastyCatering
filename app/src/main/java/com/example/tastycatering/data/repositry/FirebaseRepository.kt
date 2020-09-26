package com.example.tastycatering.data.repositry

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.tastycatering.data.model.Address
import com.example.tastycatering.data.model.Food
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

    fun getUser() = user!!.uid


    fun  getFood() :CollectionReference = firestore.collection("food")

    fun  addAddress(address: Address){

        firestore.collection("address").add(address)
            .addOnSuccessListener {
                Log.d("ok","Document Written with id ${it.id}")
            }
            .addOnFailureListener {
                Log.w("error",it.toString())
            }
    }

    }





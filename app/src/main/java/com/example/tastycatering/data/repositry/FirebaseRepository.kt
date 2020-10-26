package com.example.tastycatering.data.repositry

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.tastycatering.data.model.Address
import com.example.tastycatering.data.model.Order
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject

class FirebaseRepository @Inject constructor(


){

    private val firestoreDB = FirebaseFirestore.getInstance()
    private val storageRef = FirebaseStorage.getInstance().reference

    private val user = FirebaseAuth.getInstance().currentUser
    private val error:MutableLiveData<Boolean> = MutableLiveData(false)
    //private val response:MutableLiveData<Response> = MutableLiveData(Response(null,true,null))
    //private var response = Response(null,true,null)



    fun getUser() = user?.uid

    fun  getFood() :CollectionReference = firestoreDB.collection("food")

    fun  addAddress(address: Address): Boolean {

        firestoreDB.collection("address").add(address)
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

    fun getAllAddress()  = firestoreDB.collection("address").whereEqualTo("user_id",getUser())

    fun getFoodData(foodId:String)=
      firestoreDB.collection("food").whereEqualTo("food_id",foodId)

    fun addOrder(order: Order):Boolean{
        firestoreDB.collection("orders")
            .add(order).addOnFailureListener{
            Log.w("error",it.toString())
                error.value = true
        }
            .addOnSuccessListener {
                Log.d("ok","Document Written with id ${it.id}")
                error.value = false
            }
        return error.value!!
    }

    fun getUserOrders() =
        firestoreDB.collection("orders").whereEqualTo("user_id",getUser())

    fun getImgUrl(): Task<Uri> = storageRef.child("food_img/Untitled-1.png").downloadUrl

    fun deleteOrder(orderId:String){
        firestoreDB.collection("orders").whereEqualTo("order_id",orderId)
            .addSnapshotListener { snapshot, error ->
                snapshot.let {
                    it?.forEach {doc->
                        firestoreDB.collection("orders").document(doc.id).delete()
                    }
                }
            }

    }


    }





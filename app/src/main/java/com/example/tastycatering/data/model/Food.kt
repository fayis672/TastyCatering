package com.example.tastycatering.data.model

data class Food(
    val food_id:String?=null,
    val name:String="",
    val price:Double=0.0,
    val min_kg:Int=0,
    val description:String=""
)
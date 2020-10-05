package com.example.tastycatering.data.model

import java.util.*

data class Order(
    val order_id:String?,
    val food_id:String?,
    val unit:String?,
    val qty:String?,
    val date_time:Date?,
    val address: Address?,
    val total_price:Int
)

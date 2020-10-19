package com.example.tastycatering.data.model

import java.sql.ClientInfoStatus
import java.util.*

data class Order(
    val order_id:String?=null,
    val user_id:String?=null,
    val food_id:String?=null,
    val unit:String?=null,
    val qty:String?=null,
    val date_time:Date?=null,
    val address: Address?=null,
    val total_price: Double? =0.00,
    val status: String?=null
)

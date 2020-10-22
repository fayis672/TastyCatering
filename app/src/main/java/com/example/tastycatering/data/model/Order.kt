package com.example.tastycatering.data.model



data class Order(
    val order_id:String?=null,
    val user_id:String?=null,
    val food:Food?=null,
    val unit:String?=null,
    val qty:String?=null,
    val date_time:Date?=null,
    val address: Address?=null,
    val total_price: Double? =0.00,
    val status: String?=null
)

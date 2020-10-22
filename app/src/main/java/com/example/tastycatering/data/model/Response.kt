package com.example.tastycatering.data.model

data class Response(
    val data:List<Any>?,
    val error:Boolean,
    val msg:String?=null

)
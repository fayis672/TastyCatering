package com.example.tastycatering.data.model

data class ErrorOrder(
    val qty_check:Boolean?=true,
    val date_Check:Boolean?,
    val address_check:Boolean?,
    val msg:String?
)

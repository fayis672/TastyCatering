package com.example.tastycatering.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "selectedFood")
data class SelectedFood(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id:String ="",

    @ColumnInfo(name = "name")
    var name:String="",

   @ColumnInfo(name="kg")
    var kg:Int=0,

   @ColumnInfo(name = "tprice")
    var tprice:Double=0.0
)




package com.example.tastycatering.data.db

import androidx.room.*
import com.example.tastycatering.data.db.entity.SelectedFood

@Dao
interface SelectedItemDao {

    @Insert()
    fun addItem(selectedFood: SelectedFood)

    @Delete()
    fun deleteItem(selectedFood: SelectedFood)

    @Update()
    fun updateItem(selectedFood: SelectedFood)

    @Query("select * from selectedFood")
    fun getAll() : List<SelectedFood>

    @Query("delete from selectedFood")
    fun deleteAll()
}

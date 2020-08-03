package com.example.tastycatering.data.repositry

import com.example.tastycatering.data.db.SelectedItemDao
import com.example.tastycatering.data.db.entity.SelectedFood
import javax.inject.Inject

class SqLiteRepo @Inject constructor(
    private val selectedItemDao: SelectedItemDao
) {

    suspend fun getSelectedItems() = selectedItemDao.getAll()
    suspend fun deleteSelectedItems() = selectedItemDao.deleteAll()
    suspend fun addSelectedItem(selectedFood: SelectedFood) = selectedItemDao.addItem(selectedFood)
    suspend fun deleteSelectedItem(selectedFood: SelectedFood) = selectedItemDao.deleteItem(selectedFood)

}
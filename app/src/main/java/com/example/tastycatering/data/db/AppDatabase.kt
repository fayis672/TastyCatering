package com.example.tastycatering.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tastycatering.data.db.entity.SelectedFood


@Database(entities = [SelectedFood::class],version = 1,exportSchema = false)
abstract class AppDatabase  : RoomDatabase() {
   abstract fun selectedItemDao():SelectedItemDao
   }

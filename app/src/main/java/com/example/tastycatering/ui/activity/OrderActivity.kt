package com.example.tastycatering.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastycatering.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
    }
}
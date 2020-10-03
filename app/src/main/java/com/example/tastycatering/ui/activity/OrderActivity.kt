package com.example.tastycatering.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.tastycatering.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar.*

@AndroidEntryPoint
class OrderActivity : AppCompatActivity() {

    lateinit var navController : NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        findNavController(R.id.nav_host_fragment_container)
            .setGraph(R.navigation.order_nav,intent.extras)

        setSupportActionBar(app_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)

    }
}
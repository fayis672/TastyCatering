package com.example.tastycatering.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.tastycatering.R
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpNavigation()
    }

    private fun setUpNavigation() {
         navController = findNavController(R.id.nav_host_fragment_container)
         appBarConfiguration = AppBarConfiguration(navController.graph,main_drawer)
        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)
    }


//    override fun onNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_container)
//
//        return super.onNavigateUp() || navController.navigateUp(appBarConfiguration)
//    }
}
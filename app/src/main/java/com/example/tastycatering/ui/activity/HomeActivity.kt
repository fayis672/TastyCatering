package com.example.tastycatering.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycatering.R
import com.example.tastycatering.adapter.FoodReAdapter
import com.example.tastycatering.data.model.Food
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tool_bar.title=""

        val toggle = ActionBarDrawerToggle(this,main_drawer,tool_bar,R.string.drawer_closed,R.string.drawer_open)
        main_drawer.addDrawerListener(toggle)
        toggle.syncState()

        setUpNavigation()



    }

    fun setUpNavigation() {

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment) as NavHostFragment?
        navController = navHostFragment!!.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)

    }

}
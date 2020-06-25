package com.example.tastycatering.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tastycatering.R
import com.example.tastycatering.adapter.FoodReAdapter
import com.example.tastycatering.model.Food
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        tool_bar.title=""

        val toggle = ActionBarDrawerToggle(this,main_drawer,tool_bar,R.string.drawer_closed,R.string.drawer_open)
        main_drawer.addDrawerListener(toggle)
        toggle.syncState()
        val food: ArrayList<Food> = ArrayList()
        val food1 :Food = Food("chicken biriyani",600,5)
        val food2 :Food = Food("chilli",1000,2)
        food.add(food1)
        food.add(food2)
        food.add(food1)
        food.add(food2)
        food.add(food1)
        food.add(food2)
        food.add(food1)
        food.add(food2)


        re_food.layoutManager= LinearLayoutManager(this)
        re_food.adapter= FoodReAdapter(food,this)


    }

}
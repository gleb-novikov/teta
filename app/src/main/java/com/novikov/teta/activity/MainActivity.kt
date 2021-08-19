package com.novikov.teta.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.novikov.teta.R

class MainActivity : AppCompatActivity() {
    private val viewModel: ActivityViewModel by viewModels()
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var fragmentNavigation: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        fragmentNavigation = findNavController(R.id.fragmentNavigation)
        bottomNavigation = findViewById(R.id.bottomNavigationMain)
        bottomNavigation.setupWithNavController(fragmentNavigation)

        viewModel.initCurrentMoviesToDB()
    }
}

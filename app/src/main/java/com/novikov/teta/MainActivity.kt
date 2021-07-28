package com.novikov.teta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initActivity()
    }

    private fun initActivity() {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationMain)
        val fragmentNavigation = findNavController(R.id.fragmentNavigation)
        bottomNavigation.setupWithNavController(fragmentNavigation)
    }
}

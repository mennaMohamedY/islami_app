package com.example.quranapplication.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.quranapplication.HomeFragment.HomeFragment
import com.example.quranapplication.R
import com.example.quranapplication.ahadeeth.AhadeethFragment
import com.example.quranapplication.databinding.ActivityMainBinding
import com.example.quranapplication.recitation.QuranRecitationFragment
import com.example.quranapplication.tasbeh.TasbehFragment

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var mainVM: MainViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navView = mainBinding.btmNavigationView
        navView.setupWithNavController(navController)

    }
}
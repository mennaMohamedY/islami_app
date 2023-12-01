package com.example.quranapplication.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quranapplication.HomeFragment.HomeFragment
import com.example.quranapplication.R
import com.example.quranapplication.ahadeeth.AhadeethFragment
import com.example.quranapplication.databinding.ActivityMainBinding
import com.example.quranapplication.recitation.QuranRecitationFragment
import com.example.quranapplication.tasbeh.TasbehFragment

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding:ActivityMainBinding
    lateinit var mainVM:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainVM=ViewModelProvider(this).get(MainViewModel::class.java)
        pushFragment(HomeFragment())
        mainBinding.btmNavigationView.setOnItemSelectedListener {

            bindFragment(it)
            return@setOnItemSelectedListener true
        }
    }

    fun pushFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(mainBinding.framLayout.id,fragment).commit()
    }

    fun bindFragment(it:MenuItem){
        if(it.itemId==R.id.homee){
            pushFragment(HomeFragment())
        }else if(it.itemId==R.id.hadeeth){
            pushFragment(AhadeethFragment())
        }else if(it.itemId==R.id.tasbeh){
            pushFragment(TasbehFragment())
        }else if(it.itemId==R.id.radio){
            pushFragment(QuranRecitationFragment())
        }
    }
}
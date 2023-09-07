package com.example.islami_app_try2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.islami_app_try2.fragments.hadeethFragment
import com.example.islami_app_try2.fragments.quranFragment
import com.example.islami_app_try2.fragments.radioFragment
import com.example.islami_app_try2.fragments.tasbehFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView:BottomNavigationView
    lateinit var mode_btn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView=findViewById(R.id.bottomNavigationView)
        onitemClicked()
        bottomNavigationView.selectedItemId=R.id.quran
        mode_btn=findViewById(R.id.modeBtn)
        changeMode()

    }

    fun onitemClicked(){
        bottomNavigationView.setOnItemSelectedListener {
            if(it.itemId==R.id.quran){
                pushFragment(quranFragment())
            }else if(it.itemId==R.id.sebha){
                pushFragment(tasbehFragment())
            }else if(it.itemId==R.id.ahadeeth){
                pushFragment(hadeethFragment())
            }else if (it.itemId==R.id.radio){
                pushFragment(radioFragment())
            }
            return@setOnItemSelectedListener true
        }
    }

    fun pushFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.framLayout,fragment).commit()
    }

    fun readHadethFile(){
        val filename="ahadeth.txt"
        val fileContent=assets.open(filename).bufferedReader().use { it.readText() }
        val ahadeth_content_array=fileContent.split("#")
    }

    fun changeMode(){
        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES){
           mode_btn.text="Light"
        }else{
            mode_btn.text="Dark"
        }

        mode_btn.setOnClickListener(View.OnClickListener {
            if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                mode_btn.text="dark"
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                mode_btn.text="Light"

            }
        })
    }
}
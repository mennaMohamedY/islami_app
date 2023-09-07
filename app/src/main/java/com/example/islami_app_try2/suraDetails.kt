package com.example.islami_app_try2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class suraDetails : AppCompatActivity() {
    lateinit var detailed_suraNAme:TextView
    lateinit var Text_scrollView:TextView
    var suraIndex:Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sura_details)

        val get_suraName=intent.getStringExtra(constants.key_suraName)
        suraIndex=intent.getIntExtra(constants.key_suraPosition,-1)

        detailed_suraNAme=findViewById(R.id.detailed_suraName)
        Text_scrollView=findViewById(R.id.textView_scrollView)

        detailed_suraNAme.text=get_suraName
        readFileText()


    }

    fun readFileText(){
        val filename="${suraIndex?.plus(1)}.txt"
        val fileContent= assets.open(filename).bufferedReader().use { it.readText() }
        val content_array=fileContent.split("\n")
        var howMany_lines = content_array.size-1
        for (i in 0..howMany_lines){
            Text_scrollView.append(content_array.get(i).toString()+"(${i+1})\n")
        }
    }



}
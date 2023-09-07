package com.example.islami_app_try2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class hadeeth_details : AppCompatActivity() {

    lateinit var detailed_hadeethNum:TextView
    lateinit var detailed_hadethContent:TextView
    var hadeth_position:Int?=null
    var hadeth_num_inArabic:ArrayList<String>?=null
    var hadeethContent:List<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadeeth_details)


        detailed_hadeethNum=findViewById(R.id.detailed_hadeethName)
        detailed_hadethContent=findViewById(R.id.textView_scrollView_hadeth)

        readHadethFile()
        hadeth_position= intent.getIntExtra(constants.key_hadeethPosition,-1)
        detailed_hadeethNum.text=hadeth_num_inArabic?.get(hadeth_position!!)
        detailed_hadethContent.text=hadeethContent?.get(hadeth_position!!)
    }

    fun readHadethFile(){
        val filename="ahadeth.txt"
        hadeth_num_inArabic= ArrayList()
        val fileContent=assets.open(filename).bufferedReader().use { it.readText() }
        hadeethContent= ArrayList()
        //val ahadeth_content_array=fileContent.split("#")

        hadeethContent=fileContent.split("#")

        val howMany_hadeth= hadeethContent!!.size-1
        for (i in 0..howMany_hadeth){


            //get each hadeeth and split each new line , and return a list
            val hadethName_fromContentArray= hadeethContent!!.get(i).split("\nع")

            //we need only the first line of each hadeeth as it contain the hadeeth name,ex:الحديث الاول  will be in position 0
            //after the next line we will have an array with all of the ahadeth's name
            hadeth_num_inArabic!!.add(i,hadethName_fromContentArray[0])
        }
    }
}
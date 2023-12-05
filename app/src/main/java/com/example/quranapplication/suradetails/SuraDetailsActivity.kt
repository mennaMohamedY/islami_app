package com.example.quranapplication.suradetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import androidx.navigation.navArgument
import com.example.quranapplication.R
import com.example.quranapplication.databinding.ActivitySuraDetailsBinding
import com.example.quranapplication.model.SurahsItem

class SuraDetailsActivity : AppCompatActivity() {
    lateinit var SuraDetailsBinding:ActivitySuraDetailsBinding
    lateinit var SuraDetailsVM:SuraDetailsViewModel
    val args:SuraDetailsActivityArgs by navArgs()

    companion object{
        var SuraDetails:SurahsItem?=null
        var HadeethDetails:String?=null
        var hadeethNum:Int?=null
//
//        fun getInstance2(ahadeethDetails:String,position:Int):SuraDetailsActivity{
//            HadeethDetails=ahadeethDetails
//            hadeethNum=position
//            return SuraDetailsActivity()
//        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SuraDetailsBinding=ActivitySuraDetailsBinding.inflate(layoutInflater)
        setContentView(SuraDetailsBinding.root)
        SuraDetails=args.sis
        HadeethDetails=args.hadeethdetails
        hadeethNum=args.hadeethnum


        SuraDetailsVM=ViewModelProvider(this).get(SuraDetailsViewModel::class.java)
        if (SuraDetails !=null){
            SuraDetailsBinding.suraName.text= SuraDetails?.name
            for (i in 0..SuraDetails?.ayahs?.size!!-1){
                SuraDetailsBinding.suraDetails.append(" (${i}) ")
                SuraDetailsBinding.suraDetails.append(SuraDetails?.ayahs!![i]?.text)
            }
        }
        if (HadeethDetails !=null){
            SuraDetailsBinding.suraName.text="الحديث " + "${hadeethNum!!+1}"
            SuraDetailsBinding.suraDetails.text= HadeethDetails
        }

        SuraDetailsBinding.backbtn.setOnClickListener({
            finish()
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        SuraDetails=null
        HadeethDetails=null
    }

    override fun onPause() {
        super.onPause()
        SuraDetails=null
        HadeethDetails=null
    }
}
package com.example.quranapplication.allquransuras

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quranapplication.R
import com.example.quranapplication.databinding.ActivityAllQuranSurasBinding
import com.example.quranapplication.listentosura.ListenToSuraActivity
import com.example.quranapplication.model.APIManager
import com.example.quranapplication.model.DataItem
import com.example.quranapplication.model.SurahsItem
import kotlinx.coroutines.launch

class AllQuranSurasActivity : AppCompatActivity() {
    lateinit var AllQuranBinding:ActivityAllQuranSurasBinding
    lateinit var AllQuranSurasVM:AllQuranSurasViewModel
    var surasAdapter=SuarasAdapter(mutableListOf())

    companion object{
        var sheikhData:DataItem?=null
        fun getInstance(qareaaData:DataItem):AllQuranSurasActivity{
            sheikhData=qareaaData
            return AllQuranSurasActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AllQuranBinding=ActivityAllQuranSurasBinding.inflate(layoutInflater)
        setContentView(AllQuranBinding.root)
        GetAllQuranData()
        AllQuranBinding.surasRV.adapter=surasAdapter
        AllQuranSurasVM=ViewModelProvider(this).get(AllQuranSurasViewModel::class.java)
        surasAdapter.onSuraClickListener=object :SuarasAdapter.OnSuraClickListener{
            override fun OnSuraClick(suradata: SurahsItem, position: Int) {
                val intent=Intent(this@AllQuranSurasActivity,
                    ListenToSuraActivity.getInstance(sheikhData!!,suradata)::class.java)
                startActivity(intent)
            }
        }
        AllQuranBinding.backbtn.setOnClickListener({
            finish()
        })
    }

    fun GetAllQuranData(){
        lifecycleScope.launch {
            try {
               val response= APIManager.getServices().getAllQuran("quran/quran-uthmani -")
                surasAdapter.UpdateData(response.data?.surahs)
            }catch (e:Exception){
                showDialog(e.localizedMessage)

            }
        }
    }

    fun showDialog(e:String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("${e}")
            .setTitle("Warning!")
            .setPositiveButton("Positive") { dialog, which ->
                // Do something.
            }
            .setNegativeButton("cancel") { dialog, which ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}
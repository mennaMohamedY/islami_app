package com.example.quranapplication.HomeFragment

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapplication.model.APIManager
import com.example.quranapplication.model.QuranResponse
import com.example.quranapplication.model.QurannResponse
import com.example.quranapplication.model.SurahsItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel:ViewModel() {

    var quranData=MutableLiveData<List<SurahsItem?>?>()
    var nav:HomeNavigator?=null

    fun getQuranFromApi(context: Context){
        viewModelScope.launch {
            try {
                nav?.hideProgressBar()
                val response=APIManager.getServices().getAllQuran("quran/quran-uthmani -")
                quranData.value=response.data?.surahs
            }catch (e:Exception){
                showDialog(context,e.localizedMessage)
                Log.e("error","exception ${e}")
            }
        }
    }

//    fun getQuranFromApi(context: Context){
//        APIManager.getServices().getAllQuran("quran/quran-uthmani -").enqueue(object :Callback<QurannResponse>{
//            override fun onResponse(
//                call: Call<QurannResponse>,
//                response: Response<QurannResponse>
//            ) {
//                quranData.value=response.body()?.data?.surahs
//                showDialog(context,"Success!!")
//                Log.e("success","success")
//
//            }
//
//            override fun onFailure(call: Call<QurannResponse>, t: Throwable) {
//                showDialog(context,t.localizedMessage)
//                                Log.e("error","exception ${t.localizedMessage}")
//
//            }
//
//        })
//    }
    fun showDialog(context:Context,e:String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
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
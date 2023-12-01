package com.example.quranapplication.recitation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quranapplication.HomeFragment.HomeNavigator
import com.example.quranapplication.model.APIManager
import com.example.quranapplication.model.DataItem
import kotlinx.coroutines.launch

class QuranRecitationViewModel :ViewModel() {
    var Qoraa=MutableLiveData<List<DataItem?>?>()
    var nav:QuranRecitationNavigator?=null


    fun getAllQoraa(){
        viewModelScope.launch {
            try {
                val response=APIManager.getServices().getAllQoraa("edition","audio")
                nav?.hideProgressBar()
                Qoraa.value=response.data
            }catch (e:Exception){
                nav?.showAlertDialog(e.localizedMessage)

            }
        }
    }
}
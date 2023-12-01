package com.example.quranapplication.HomeFragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.quranapplication.R
import com.example.quranapplication.databinding.FragmentHomeBinding
import com.example.quranapplication.model.APIManager
import com.example.quranapplication.model.QuranResponse
import com.example.quranapplication.model.SurahsItem
import com.example.quranapplication.suradetails.SuraDetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(),HomeNavigator {
    lateinit var HomeBinding:FragmentHomeBinding
    lateinit var HomeVM:HomeViewModel
    var homeAdapter=HomeAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeVM=ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        HomeBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        return HomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomeVM.getQuranFromApi(requireContext())
        subscribeToLiveData()
        HomeBinding.scdRv.adapter=homeAdapter
        HomeVM.nav=this
        homeAdapter.onSuraClickListener=object :HomeAdapter.OnSuraClickListener{
            override fun OnSuraClick(sura: SurahsItem, position: Int) {
                val intent= Intent(requireContext(),SuraDetailsActivity.getInstance(sura)::class.java)
                startActivity(intent)
            }
        }
    }

    fun subscribeToLiveData(){
        HomeVM.quranData.observe(viewLifecycleOwner){
            homeAdapter.updateData(it)
        }
        hideProgressBar()

    }

    override fun hideProgressBar() {
        HomeBinding.ayatNumProgressbar.visibility=View.INVISIBLE
    }


}
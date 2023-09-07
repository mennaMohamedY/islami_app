package com.example.islami_app_try2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami_app_try2.R
import com.example.islami_app_try2.adapters.hadethAdapter
import com.example.islami_app_try2.constants
import com.example.islami_app_try2.hadeeth_details
import java.nio.channels.AsynchronousFileChannel.open


class hadeethFragment : Fragment() {

    lateinit var hadeeth_recyclerView:RecyclerView
    lateinit var hadeeth_adapter:hadethAdapter
    lateinit var hadeth_Data:ArrayList<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hadeeth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hadeeth_recyclerView=view.findViewById(R.id.hadeeth_recyclerView)
        initAhadeeth()
        hadeeth_adapter= hadethAdapter(hadeth_Data)
        hadeeth_recyclerView.adapter=hadeeth_adapter

        hadeeth_adapter.hadeethonCLickListener=object :hadethAdapter.hadethOnClickListener{
            override fun hadethOnClicked(position: Int, item: String) {
                val intent1=Intent(requireContext(),hadeeth_details::class.java)
                intent1.putExtra(constants.key_hadeethPosition,position)
                startActivity(intent1)
            }
        }
    }

    fun initAhadeeth(){
        hadeth_Data= ArrayList()
        for(i in 0..49){
            hadeth_Data.add("الحديث رقم  ${i+1}")
        }
    }




}
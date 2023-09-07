package com.example.islami_app_try2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami_app_try2.R

class hadethAdapter(var hadethDAta:ArrayList<String>) :Adapter<hadethAdapter.MyAdapter>(){

    var hadeethonCLickListener:hadethOnClickListener?=null
    class MyAdapter(itemview:View):ViewHolder(itemview){

        val hadeeth_name:TextView
        init {
            hadeeth_name=itemview.findViewById(R.id.detailed_hadeethName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.hadeeth_singleelement,parent,false)
        return MyAdapter(view)
    }

    override fun onBindViewHolder(holder: MyAdapter, position: Int) {
        holder.hadeeth_name.text=hadethDAta.get(position)
        holder.hadeeth_name.setOnClickListener(View.OnClickListener {
            hadeethonCLickListener?.hadethOnClicked(position,hadethDAta.get(position))

        })
    }
    interface hadethOnClickListener{
        fun hadethOnClicked(position: Int,item:String)
    }

    override fun getItemCount(): Int {
        return hadethDAta.size
    }

}
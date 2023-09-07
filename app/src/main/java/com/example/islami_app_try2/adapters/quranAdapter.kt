package com.example.islami_app_try2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami_app_try2.R
import com.example.islami_app_try2.onSuraClickListener

class quranAdapter(val suraData: Array<String>, val count:Int=286) :Adapter<quranAdapter.MyHolder>() {

   var onsuraClickListener :onSuraClickListener?=null
    class MyHolder(itemview:View):ViewHolder(itemview){
         var suraname:TextView
         var ayatNum:TextView
        init {
            suraname=itemview.findViewById(R.id.sura_name)
            ayatNum=itemview.findViewById(R.id.ayat_num)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.sora_ayat_singleelementdesign,parent,false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.suraname.text=suraData.get(position)
        holder.ayatNum.text=count.toString()
        holder.suraname.setOnClickListener(View.OnClickListener {
            onsuraClickListener?.onSuraClick(position,suraData[position])
        })
    }


    override fun getItemCount(): Int {
        return suraData.size
    }


}

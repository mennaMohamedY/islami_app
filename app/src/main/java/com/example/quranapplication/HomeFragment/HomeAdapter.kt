package com.example.quranapplication.HomeFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quranapplication.databinding.SinglesuraNamedesignBinding
import com.example.quranapplication.model.QuranResponse
import com.example.quranapplication.model.SurahsItem

class HomeAdapter(var SuraData:List<SurahsItem?>?):Adapter<HomeAdapter.MySuraHolder>() {

    var onSuraClickListener:OnSuraClickListener?=null
    fun updateData(suraData:List<SurahsItem?>?){
        SuraData=suraData
        notifyDataSetChanged()
    }


    class MySuraHolder(val suraBinding:SinglesuraNamedesignBinding):ViewHolder(suraBinding.root){
        fun bind(suraItem:SurahsItem){
            suraBinding.vm=suraItem
            suraBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySuraHolder {
        val suraBinding=SinglesuraNamedesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MySuraHolder(suraBinding)

    }

    override fun onBindViewHolder(holder: MySuraHolder, position: Int) {
        val currentItem=SuraData?.get(position)
//        holder.bind(currentItem!!)
        holder.suraBinding.suraName.text=currentItem?.name.toString()
        holder.suraBinding.ayatNum.text=currentItem?.ayahs?.size.toString()
        holder.suraBinding.suraName.setOnClickListener({
            onSuraClickListener?.OnSuraClick(currentItem!!,position)
        })

    }

    interface OnSuraClickListener{
        fun OnSuraClick(sura:SurahsItem,position: Int)
    }

    override fun getItemCount(): Int {
       return SuraData?.size?:0
    }

}
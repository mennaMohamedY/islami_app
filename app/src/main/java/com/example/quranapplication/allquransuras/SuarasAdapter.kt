package com.example.quranapplication.allquransuras

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quranapplication.databinding.SinglesuraDesignBinding
import com.example.quranapplication.model.DataItem
import com.example.quranapplication.model.SurahsItem
import com.example.quranapplication.model.SurahsItemm

class SuarasAdapter(var surasData:List<SurahsItem?>?):Adapter<SuarasAdapter.SurasHolder>() {
    var onSuraClickListener:OnSuraClickListener?=null
    fun UpdateData(suras:List<SurahsItem?>?){
        surasData=suras
        notifyDataSetChanged()
    }
    class SurasHolder(val surasBinding:SinglesuraDesignBinding):ViewHolder(surasBinding.root){
        fun bind(surasData:DataItem){}

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurasHolder {
        val surasBinding=SinglesuraDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SurasHolder(surasBinding)
    }

    override fun onBindViewHolder(holder: SurasHolder, position: Int) {
        val currentItem=surasData?.get(position)
        holder.surasBinding.suraname.text=currentItem?.name
        holder.surasBinding.suraContainer.setOnClickListener({
            onSuraClickListener?.OnSuraClick(currentItem!!,position)
        })

    }
    interface OnSuraClickListener{
        fun OnSuraClick(suradata:SurahsItem,position: Int)
    }

    override fun getItemCount(): Int {
        return surasData?.size?:0
    }
}
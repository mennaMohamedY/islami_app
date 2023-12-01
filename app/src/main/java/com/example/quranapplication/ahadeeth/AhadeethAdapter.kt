package com.example.quranapplication.ahadeeth

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quranapplication.databinding.SinglhadeethDesignBinding

class AhadeethAdapter (var ahadeeth:MutableList<String?>?):Adapter<AhadeethAdapter.AhadeethHolder>(){

    var onHadeethNumClickListener:OnHadeethNumClickListener?=null
    fun updateAhadeeth(haddeth:MutableList<String?>?){
        ahadeeth=haddeth
        notifyDataSetChanged()
    }

    class AhadeethHolder(val hadeethBinding:SinglhadeethDesignBinding):ViewHolder(hadeethBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AhadeethHolder {
        val hadeethBinding=SinglhadeethDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AhadeethHolder(hadeethBinding)
    }

    override fun onBindViewHolder(holder: AhadeethHolder, position: Int) {
        val currentItem=ahadeeth?.get(position)
        holder.hadeethBinding.hadeethNum.text = currentItem + "${position +1 }"
        holder.hadeethBinding.hadeethNum.setOnClickListener({
            onHadeethNumClickListener?.OnHadeethNumClick(currentItem!!,position)
        })
    }
    interface OnHadeethNumClickListener{
        fun OnHadeethNumClick(hadeeth:String,position: Int)
    }

    override fun getItemCount(): Int {
        return ahadeeth?.size?:0
    }
}
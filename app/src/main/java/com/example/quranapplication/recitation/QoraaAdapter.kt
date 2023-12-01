package com.example.quranapplication.recitation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.quranapplication.databinding.SingleqareaaDesignBinding
import com.example.quranapplication.model.DataItem
import com.example.quranapplication.model.QoraaResponse

class QoraaAdapter(var QareaaData:List<DataItem?>?) :Adapter<QoraaAdapter.QoraaHolder>(){

    var onSheikhClickListener:OnSheikhClickListener?=null
    fun updateQoraa(qoraa:List<DataItem?>?){
        QareaaData=qoraa
        notifyDataSetChanged()
    }
    class QoraaHolder(val qoraaBinding:SingleqareaaDesignBinding):ViewHolder(qoraaBinding.root){
        fun bind(qareaaData:DataItem){
            qoraaBinding.vm=qareaaData
            qoraaBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QoraaHolder {
        val qoraaBinding=SingleqareaaDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QoraaHolder(qoraaBinding)
    }


    override fun onBindViewHolder(holder: QoraaHolder, position: Int) {
        val currentItem=QareaaData?.get(position)
        holder.bind(currentItem!!)
        holder.qoraaBinding.constraintlo.setOnClickListener({
            onSheikhClickListener?.OnSheikhClick(currentItem,position)
        })

    }
    interface OnSheikhClickListener{
        fun OnSheikhClick(QareaaData:DataItem,position: Int)
    }

    override fun getItemCount(): Int {
        return QareaaData?.size?:0
    }
}
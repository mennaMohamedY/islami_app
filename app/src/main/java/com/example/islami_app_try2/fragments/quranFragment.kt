package com.example.islami_app_try2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.islami_app_try2.R
import com.example.islami_app_try2.adapters.quranAdapter
import com.example.islami_app_try2.classes.ArSuras
import com.example.islami_app_try2.classes.surasData
import com.example.islami_app_try2.constants
import com.example.islami_app_try2.onSuraClickListener
import com.example.islami_app_try2.suraDetails


class quranFragment : Fragment() {

    lateinit var quranRecyclerView:RecyclerView
    lateinit var QuranAdapter:quranAdapter
    lateinit var quranData:ArrayList<surasData>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quranRecyclerView=view.findViewById(R.id.quran_recyclerView)
        QuranAdapter=quranAdapter(ArSuras)
        quranRecyclerView.adapter=QuranAdapter

        QuranAdapter.onsuraClickListener=object :onSuraClickListener{
            override fun onSuraClick(position: Int, Item: String) {
                val intent = Intent(requireContext(),suraDetails::class.java)
                intent.putExtra(constants.key_suraName,Item)
                intent.putExtra(constants.key_suraPosition,position)
                startActivity(intent)

            }
        }
    }



}
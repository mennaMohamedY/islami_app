package com.example.quranapplication.recitation

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.quranapplication.R
import com.example.quranapplication.allquransuras.AllQuranSurasActivity
import com.example.quranapplication.databinding.FragmentQuranRecitationBinding
import com.example.quranapplication.model.DataItem

class QuranRecitationFragment : Fragment(),QuranRecitationNavigator {
    lateinit var QuranRecitationBinding:FragmentQuranRecitationBinding
    lateinit var QuranVM:QuranRecitationViewModel
    var QoraaAdapter=QoraaAdapter(listOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        QuranVM=ViewModelProvider(this).get(QuranRecitationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        QuranRecitationBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_quran_recitation,container,false)
        return QuranRecitationBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        QuranVM.getAllQoraa()
        QuranVM.nav=this
        subscribeToLiveData()
        QuranRecitationBinding.qoraaRv.adapter=QoraaAdapter
        QoraaAdapter.onSheikhClickListener=object :QoraaAdapter.OnSheikhClickListener{
            override fun OnSheikhClick(QareaaData: DataItem, position: Int) {
//                val intent= Intent(requireContext(),AllQuranSurasActivity.getInstance(QareaaData)::class.java)
//                startActivity(intent)
                val action=QuranRecitationFragmentDirections.actionQuranRecitationFragmentToAllQuranSurasActivity(QareaaData)
                findNavController().navigate(action)
            }
        }
    }

    fun subscribeToLiveData(){
        QuranVM.Qoraa.observe(viewLifecycleOwner){
            QoraaAdapter.updateQoraa(it)
        }
    }

    override fun showAlertDialog(msg: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setMessage("${msg}")
            .setTitle("Warning!")
            .setPositiveButton("Positive") { dialog, which ->
                // Do something.
            }
            .setNegativeButton("cancel") { dialog, which ->
                dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    override fun hideProgressBar() {
        QuranRecitationBinding.progressBAr.visibility=View.INVISIBLE
    }

}
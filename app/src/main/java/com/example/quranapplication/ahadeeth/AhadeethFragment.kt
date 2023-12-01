package com.example.quranapplication.ahadeeth

import android.app.Application
import android.content.Intent
import android.hardware.Camera.open
import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.system.Os.open
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quranapplication.R
import com.example.quranapplication.databinding.FragmentAhadeethBinding
import com.example.quranapplication.suradetails.SuraDetailsActivity
import java.nio.channels.FileChannel.open
import java.nio.channels.Pipe.open


class AhadeethFragment : Fragment() {
    val hadeth_name_inArabic= mutableListOf<String?>()
    var hadeethcontent= listOf<String>()
    lateinit var hadeethBinding:FragmentAhadeethBinding
    lateinit var hadeethVM:AhadeethViewModel
    var ahadeethAdapter=AhadeethAdapter(mutableListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hadeethVM=ViewModelProvider(this).get(AhadeethViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        hadeethBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_ahadeeth,container,false)
        return hadeethBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readHadethFile()
        ahadeethAdapter.updateAhadeeth(hadeth_name_inArabic!!)
        hadeethBinding.ahadeethRv.adapter=ahadeethAdapter
        ahadeethAdapter.onHadeethNumClickListener=object :AhadeethAdapter.OnHadeethNumClickListener{
            override fun OnHadeethNumClick(hadeeth: String, position: Int) {
                val getHadeth=hadeethcontent.get(position)
                val intent= Intent(requireContext(),SuraDetailsActivity.getInstance2(getHadeth,position)::class.java)
                startActivity(intent)
            }

        }

    }

    fun readHadethFile(){
        val file_name="ahadeth.txt"
        val file_content = requireContext().assets.open(file_name).bufferedReader().use{
            it.readText()
        }
        hadeethcontent=file_content.split("#")
        val ahadeethNum=hadeethcontent.size
        for(i in 0 .. ahadeethNum-1){
            val hadethName_fromContentArray= hadeethcontent!!.get(i).split("\nع")

            //we need only the first line of each hadeeth as it contain the hadeeth name,ex:الحديث الاول  will be in position 0
            //after the next line we will have an array with all of the ahadeth's name
            hadeth_name_inArabic!!.add(i,hadethName_fromContentArray[0])

        }
    }


}
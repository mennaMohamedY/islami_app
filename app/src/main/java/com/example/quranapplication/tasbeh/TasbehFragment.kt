package com.example.quranapplication.tasbeh

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quranapplication.R
import com.example.quranapplication.databinding.FragmentTasbehBinding


class TasbehFragment : Fragment() {
    lateinit var tasbehBinding: FragmentTasbehBinding
    lateinit var tasbehVM:TasbehViewModel
    var Elbaqyat_Elsalhat= arrayListOf<String>()
    var count=0
    var thirty_three_count=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasbehVM=ViewModelProvider(this).get(TasbehViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_tasbeh, container, false)
        tasbehBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_tasbeh,container,false)
        return tasbehBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initArray()


        tasbehBinding.sebhabody.setOnClickListener(View.OnClickListener {
            count+=1
            tasbehBinding.numofTasbeh.text=thirty_three_count.toString()


            if (count >= 0 && count <= 33){
                thirty_three_count+=1
                tasbehBinding.numofTasbeh.text=thirty_three_count.toString()
                tasbehBinding.albaqyatAlsalhat.setText(Elbaqyat_Elsalhat.get(0))
                if(count == 33){
                    thirty_three_count=0
                }
            }else if(count >= 34 && count <= 66){
                thirty_three_count+=1
                tasbehBinding.numofTasbeh.setText(thirty_three_count.toString())
                tasbehBinding.albaqyatAlsalhat.setText(Elbaqyat_Elsalhat.get(1))
                if(count == 66){
                    thirty_three_count=0
                }
            }else if(count >= 67 && count <= 99){
                thirty_three_count+=1
                tasbehBinding.numofTasbeh.setText(thirty_three_count.toString())
                tasbehBinding.albaqyatAlsalhat.setText(Elbaqyat_Elsalhat.get(2))
                if(count==99){
                    thirty_three_count=0
                }
            }else if(count >= 100 && count <= 132){
                thirty_three_count+=1
                tasbehBinding.numofTasbeh.setText(thirty_three_count.toString())
                tasbehBinding.albaqyatAlsalhat.setText(Elbaqyat_Elsalhat.get(3))
                if(count == 132){
                    thirty_three_count=0
                    count=0
                }
            }


        })

    }
    fun initArray(){
        Elbaqyat_Elsalhat.add(0,"سبحان الله")
        Elbaqyat_Elsalhat.add(1,"الحمد لله")
        Elbaqyat_Elsalhat.add(2,"لا اله الا الله")
        Elbaqyat_Elsalhat.add(3,"الله اكبر")
    }


}
package com.example.islami_app_try2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.islami_app_try2.R


class tasbehFragment : Fragment() {

    lateinit var sebhaBody: ImageView
    lateinit var tasbehCount: TextView
    lateinit var currentTasbeh: TextView
    var count: Int=0
    lateinit var Elbaqyat_Elsalhat : ArrayList<String>
    var thirty_three_count:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasbeeh, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sebhaBody=view.findViewById(R.id.sebha_logo)
        tasbehCount=view.findViewById(R.id.numof_tasbeh)
        currentTasbeh=view.findViewById(R.id.albaqyat_alsalhat)
        Elbaqyat_Elsalhat= arrayListOf()
        Elbaqyat_Elsalhat.add(0,"سبحان الله")
        Elbaqyat_Elsalhat.add(1,"الحمد لله")
        Elbaqyat_Elsalhat.add(2,"لا اله الا الله")
        Elbaqyat_Elsalhat.add(3,"الله اكبر")

        sebhaBody.setOnClickListener(View.OnClickListener {
            count+=1
            tasbehCount.setText(thirty_three_count.toString())


            if (count >= 0 && count <= 33){
                thirty_three_count+=1
                tasbehCount.setText(thirty_three_count.toString())
                currentTasbeh.setText(Elbaqyat_Elsalhat.get(0))
                if(count == 33){
                    thirty_three_count=0
                }
            }else if(count >= 34 && count <= 66){
                thirty_three_count+=1
                tasbehCount.setText(thirty_three_count.toString())
                currentTasbeh.setText(Elbaqyat_Elsalhat.get(1))
                if(count == 66){
                    thirty_three_count=0
                }
            }else if(count >= 67 && count <= 99){
                thirty_three_count+=1
                tasbehCount.setText(thirty_three_count.toString())
                currentTasbeh.setText(Elbaqyat_Elsalhat.get(2))
                if(count==99){
                    thirty_three_count=0
                }
            }else if(count >= 100 && count <= 132){
                thirty_three_count+=1
                tasbehCount.setText(thirty_three_count.toString())
                currentTasbeh.setText(Elbaqyat_Elsalhat.get(3))
                if(count == 132){
                    thirty_three_count=0
                    count=0
                }
            }


        })

    }
}

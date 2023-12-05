package com.example.quranapplication.listentosura

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaParser
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.quranapplication.R
import com.example.quranapplication.allquransuras.AllQuranSurasActivity
import com.example.quranapplication.databinding.ActivityListenToSuraBinding
import com.example.quranapplication.model.APIManager
import com.example.quranapplication.model.DataItem
import com.example.quranapplication.model.SurahsItem
import kotlinx.coroutines.*
import okhttp3.internal.wait
import kotlin.math.log
import kotlin.time.toDuration

class ListenToSuraActivity : AppCompatActivity() {
    lateinit var ListenToSuraBinding:ActivityListenToSuraBinding
    lateinit var ListenToSuraVM:ListenToSuraViewModel
    var isStreaming:Boolean?=false
    var  m:MediaPlayer?=null
    var ayaturl= (mutableListOf <String>())
    var ayahDuration:Int?=0
    var stop:Boolean=false

    companion object{
        var QareaaData:DataItem?=null
        var SuraDetails:SurahsItem?=null
        fun getInstance(qareaa:DataItem,sura:SurahsItem):ListenToSuraActivity{
            QareaaData=qareaa
            SuraDetails=sura
            return ListenToSuraActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_listen_to_sura)
        ListenToSuraBinding=ActivityListenToSuraBinding.inflate(layoutInflater)
        setContentView(ListenToSuraBinding.root)
        ListenToSuraVM=ViewModelProvider(this).get(ListenToSuraViewModel::class.java)
        getSuraFromApi()
        ListenToSuraBinding.suraname.text= SuraDetails?.name
        ListenToSuraBinding.qareaaname.text= " القاري الشيخ " + QareaaData?.name
        ListenToSuraBinding.backbtn.setOnClickListener({
            finish()
        })
        //showWhatsInsideList()
        ListenToSuraBinding.resume.setOnClickListener({
            ListenToSuraBinding.resume.visibility=View.INVISIBLE
            ListenToSuraBinding.pause.visibility=View.VISIBLE
            getSura()
        })

        ListenToSuraBinding.stop.setOnClickListener({
            ListenToSuraBinding.resume.visibility=View.VISIBLE
            ListenToSuraBinding.pause.visibility=View.INVISIBLE
            //Toast.makeText(this,"Stop Botton Clicked",Toast.LENGTH_LONG).show()
            Log.e("btnstop","Stop Botton Clicked")
            stop=true
            Toast.makeText(this," برجاء الانتظار سيتم الايقاف بعد انتهاء  هذه الايه ",Toast.LENGTH_LONG).show()
        })

        ListenToSuraBinding.seekbar.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(p2){
                    m!!.seekTo(p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getSura(){

        // startAudioStream(SuraLinkProvider.SuraLink!!)
        val job=GlobalScope.launch(Dispatchers.IO) {
            var i =0
            var length=SuraLinkProvider.SuraLink?.size!!-1
            while (i <= SuraLinkProvider.SuraLink!!.size-1){
                val url=SuraLinkProvider.SuraLink!![i]
                val result = async { getAudioStream(url).duration }.await()
                lifecycleScope.launch { initSeekBar() }



                Log.e("resultDuration","${result}")
                if(stop){

                    i=SuraLinkProvider.SuraLink!!.size
                    stopPlaying()
                }
                i++
            }
        }}
    fun showWhatsInsideList(){
        for (i in 0..SuraLinkProvider.SuraLink?.size!!-1){
            Log.e("list ${i}","${SuraLinkProvider.SuraLink!![i]}")
        }
    }

    fun stopPlaying():Boolean{
        if(m !=null && m!!.isPlaying){
            //getSura(true)
            m!!.stop()
            m!!.release()
            m= MediaPlayer()
            m!!.reset()
        }
        return true
    }
    var i =0

    suspend fun getAudioStream(urrl:String):MediaPlayer{

        if(m ==null)
            m = MediaPlayer()
        var isFinished=false
        try {
            //Log.e("nm","${i}")
            m = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                //duration
                setDataSource(urrl)
                prepare() // might take long! (for buffering, etc)
                start()
            }

            while (m!!.isPlaying){
                ayahDuration =ayahDuration!! + m!!.duration
            }


        }catch (e:Exception){
            Log.e("Error","Error occured while playing audio ${e.localizedMessage}")
        }
        return m as MediaPlayer

    }

    fun getSuraFromApi(){
        val Qareaaname= QareaaData?.identifier
        val suranum = SuraDetails?.number
        //SuraLinkProvider.suranum= SuraDetails!!.number
        lifecycleScope.launch  {
            try{
                val response=APIManager.getServices().getSpecificSura(suranum!!,Qareaaname!!)
                //return ayahs of sura
                val v=response.data?.ayahs
                for (i in 0..v!!.size-1){
                    //each aya audio is separated in link so we loop the array of ayahs to put all the ayahs audio in one array
                    Log.e("ayahs","ayah ${i} is add in array with audio link of ${v[i]}")

                    ayaturl.add(i,v[i]?.audio!!)
                }
                SuraLinkProvider.SuraLink=ayaturl
            }catch (e:Exception){
                showDialog(e.localizedMessage)
            }
        }


    }
    fun initSeekBar(){
        ListenToSuraBinding.seekbar.max=m!!.duration
        val handler=Handler()
        handler.postDelayed(object:java.lang.Runnable{
            override fun run() {
                try {
                    ListenToSuraBinding.seekbar.progress=m!!.currentPosition
                    handler.postDelayed(this,1000)
                }catch (e:Exception){
                    ListenToSuraBinding.seekbar.progress=0
                }
            }

        },0)
    }

    fun showDialog(e:String){
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setMessage("${e}")
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
}
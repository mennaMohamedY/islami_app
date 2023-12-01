package com.example.quranapplication.model

import android.util.Log
import com.example.quranapplication.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManager {

    companion object
    {
        private var retrofit:Retrofit?=null
        private fun getInstance():Retrofit{
            if (retrofit== null){

                Log.e("TAG", "Retrofit == null")
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                retrofit = Retrofit.Builder().client(client)
                    .baseUrl("http://api.alquran.cloud/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!
        }

        fun getServices():WebServices{
           return getInstance().create(WebServices::class.java)
        }
    }
}
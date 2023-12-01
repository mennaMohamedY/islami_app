package com.example.quranapplication.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServices {

    @GET("{quranPath}")
    suspend fun getAllQuran(@Path("quranPath") quranPath:String):QurannResponse

//    @GET()
//    fun getAllQuran(): Call<QurannResponse>

    @GET("{edition}")
    suspend fun getAllQoraa(@Path("edition") edition:String,@Query("format") format:String):QoraaResponse

    @GET("{quran}/{sheikhname}")
    suspend fun getAllQuranRecitation(@Path("quran") quran:String,@Path("sheikhname") sheikhname:String) :QuranRecitationResponse

    @GET("surah/{num}/{sheikhname}")
    suspend fun getSpecificSura(@Path("num") suraNum:Int,@Path("sheikhname") sheikhname:String):SpecificSuraResponse
}
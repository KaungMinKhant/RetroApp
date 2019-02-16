package com.hexapixel.myretroapp.network

import com.hexapixel.myretroapp.entity.Regions
import com.hexapixel.myretroapp.entity.Townships
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val API_END = "https://second.omimyanmar.org/api/"

interface MainService {
    @GET("regions")
    fun getAllRegions():Call<Regions>
//sout san interface htl mhr companion object yay lox ya al ll kotlin atwat tr

    @GET("townships/{region_code}")
    fun getTownships(@Path( "region_code") region_code:String): Call<Townships>
    companion object {
        operator fun invoke(): MainService {
            return Retrofit.Builder()
                .baseUrl(API_END)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient())
                .build()
                .create(MainService::class.java)
        }
    }
}
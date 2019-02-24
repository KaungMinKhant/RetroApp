package com.hexapixel.myretroapp.network

import com.hexapixel.myretroapp.entity.Movies
import com.hexapixel.myretroapp.entity.Regions
import com.hexapixel.myretroapp.entity.Townships
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_END = "https://api.themoviedb.org/3/"
const val API_KEY = "44d8c2a4e38999a5badd2c7bbd7e37c8"

interface MainService {

    @GET("discover/movie")
    fun getAllMovies(
        @Query("primary_release_date.gte")
        primary_release_date_gte: String,
        @Query("primary_release_date.lte")
        primary_release_date_lte: String,
        @Query("api_key") api_key: String
    ):Call<Movies>

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
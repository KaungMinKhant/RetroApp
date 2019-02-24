package com.hexapixel.myretroapp

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.hexapixel.myretroapp.entity.Movies
import com.hexapixel.myretroapp.entity.Regions
import com.hexapixel.myretroapp.entity.Townships
import com.hexapixel.myretroapp.network.API_KEY
import com.hexapixel.myretroapp.network.MainService

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val service = MainService.invoke()
        /*val regionsCall = service.getAllRegions()
        regionsCall.enqueue(object : retrofit2.Callback<Regions>{
            override fun onFailure(call: Call<Regions>, t: Throwable) {
                Log.d("My_ERror", t.toString())
            }

            override fun onResponse(call: Call<Regions>, response: Response<Regions>) {
                val regions = response.body()!!.regions
                Log.d("MY_Regions", regions.toString())
            }
        })
        val townshipCall = service.getTownships("MMR013")*/

        val moviesCall = service.getAllMovies("2014-09-15", "2014-10-22", API_KEY)
        moviesCall.enqueue(object : retrofit2.Callback<Movies>{
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("MY_ERRor", t.toString())
            }

            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                val movies = response.body()!!.results
                Log.d("My_Movie: ", movies.toString())
            }
        })
        fab.setOnClickListener { view ->
           /* townshipCall.enqueue(object : retrofit2.Callback<Townships> {
                override fun onFailure(call: Call<Townships>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Townships>, response: Response<Townships>) {
                   val townships = response.body()!!.townships
                    Log.d("MY_Townships:", townships.toString())
                }
            })*/
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

package com.hexapixel.myretroapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hexapixel.myretroapp.adapter.RegionAdapter
import com.hexapixel.myretroapp.entity.Region
import com.hexapixel.myretroapp.network.ConnectivityInterceptorImpl
import com.hexapixel.myretroapp.network.MainService
import com.hexapixel.myretroapp.network.NoConnectivityException
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var mJob: Job
    private lateinit var regionAdapter: RegionAdapter
    private val myRegion: MutableList<Region> = ArrayList()
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        regionAdapter = RegionAdapter(myRegion)

        main_recycler.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = regionAdapter
        }

        mJob = Job()
        val api = MainService(ConnectivityInterceptorImpl(this))
        launch {
            val request = api.getRegionsAsync()
            try {
                val response = request.await()
                myRegion.addAll(response.body()!!.regions)
                regionAdapter.notifyDataSetChanged()

            } catch (e: NoConnectivityException) {
                Log.d("MY_ERROR", "NO INTERNET!")
            } catch (e: Throwable) {
                Log.d("MY_ERROR", "Something went wroung")
            }
        }

        fab.setOnClickListener { view ->
            startActivity(Intent(MainActivity@this, RegionActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
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
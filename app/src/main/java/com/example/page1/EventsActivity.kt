package com.example.page1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.page1.adapters.EventsAdapter
import com.example.page1.adapters.SelectedCityImagesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale


class EventsActivity : AppCompatActivity() {

    private var selectedCategory: String? = null
    private var selectedCity: String? = null
    private var filteringByCity: Boolean = false
    private lateinit var backendService: BackendService

    private lateinit var eventRecyclerView: RecyclerView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)


        selectedCategory = intent.getStringExtra("selectedCategory").toString()
        selectedCity = intent.getStringExtra("selectedCity").toString()

        Log.d("seeleeectionssss", "cat: $selectedCategory - city: $selectedCity")

        filteringByCity = selectedCategory == null


        val toolbar: Toolbar = findViewById(R.id.athens_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        // First RecyclerView for Image Gallery
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        val athensImageList = listOf(
            R.drawable.ath_1,
            R.drawable.ath_3,
            R.drawable.ath_5,
            R.drawable.ath_6,
            R.drawable.ath_8,
            R.drawable.ath_10,
            R.drawable.ath_11,
            R.drawable.ath_12,
            R.drawable.eventsnap_
        )
        val parisImageList = listOf(
            R.drawable.paris_1,
            R.drawable.paris_2,
            R.drawable.paris_3,
            R.drawable.eventsnap_,
            R.drawable.paris_4,
            R.drawable.paris_5,
            R.drawable.paris_6,
            R.drawable.paris_7,
            R.drawable.paris_8,
            R.drawable.paris_9,
            R.drawable.paris_10
        )
        val romeImageList = listOf(
            R.drawable.rome_1,
            R.drawable.rome_2,
            R.drawable.rome_3,
            R.drawable.rome_4,
            R.drawable.eventsnap_,
            R.drawable.rome_5,
            R.drawable.rome_6,
            R.drawable.rome_7,
            R.drawable.rome_8,
            R.drawable.rome_9,
            R.drawable.rome_10
        )
        val londonImageList = listOf(
            R.drawable.london_1,
            R.drawable.london_2,
            R.drawable.london_3,
            R.drawable.london_4,
            R.drawable.eventsnap_,
            R.drawable.london_5,
            R.drawable.london_6,
            R.drawable.london_7,
            R.drawable.london_8,
            R.drawable.london_9,
            R.drawable.london_10
        )
        val selectedCityImageList = mutableListOf<Int>()
        when(selectedCity) {
            "athens" -> selectedCityImageList.addAll(athensImageList)
            "paris" -> selectedCityImageList.addAll(parisImageList)
            "rome" -> selectedCityImageList.addAll(romeImageList)
            "london" -> selectedCityImageList.addAll(londonImageList)
        }

        val adapter = SelectedCityImagesAdapter(this, selectedCityImageList) { position ->
            val intent = Intent(this, ImageViewerActivity::class.java)
            intent.putExtra("imageResource", selectedCityImageList[position])
            startActivity(intent)
        }



        // Second RecyclerView for Events
        eventRecyclerView = findViewById(R.id.athensVertical)
        val currentEventsText = findViewById<TextView>(R.id.textView8)
        val titleText = findViewById<TextView>(R.id.textView4)

        if(selectedCity.isNullOrBlank() || selectedCity == "null") {
            titleText.text = selectedCategory.toString().capitalize(Locale.ROOT)
            recyclerView.visibility = View.GONE
            currentEventsText.visibility = View.GONE
        } else {
            currentEventsText.visibility = View.VISIBLE
            currentEventsText.text = "Current events in ${selectedCity.toString().capitalize(Locale.ROOT)}"
            titleText.text = selectedCity.toString().capitalize(Locale.ROOT)
            recyclerView.adapter = adapter
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = layoutManager
        }

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-snap.cyclic.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        backendService = retrofit.create(BackendService::class.java)

        fetchEvents()

        //until here
        /**   private fun updateUiWithData(eventList: List<AthensEventItems>) {*/

    }

    private fun fetchEvents() {

        val locationParam = if(selectedCity.isNullOrBlank() || selectedCity == "null") null else selectedCity.toString()
        val eventTypeParam = if(selectedCategory.isNullOrBlank() || selectedCategory == "null") null else selectedCategory.toString()
        val call = backendService.getEvents(location = locationParam, eventType = eventTypeParam)

        call.enqueue(object : Callback<List<EventsDataResponse>> {
            override fun onResponse(call: Call<List<EventsDataResponse>>, response: Response<List<EventsDataResponse>>) {
                if (response.isSuccessful) {
                    val eventsList = response.body()
                    if (eventsList != null) {
                        // Update the RecyclerView with the data from the backend
                        updateRecyclerView(eventsList)
                    }
                } else {
                    // Handle error
                    Toast.makeText(
                        this@EventsActivity,
                        "Error: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<EventsDataResponse>>, t: Throwable) {
                // Handle failure
                Toast.makeText(this@EventsActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateRecyclerView(eventsList: List<EventsDataResponse>) {
        val adapter = EventsAdapter(this, eventsList)
        eventRecyclerView.adapter = adapter
        val eventLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        eventRecyclerView.layoutManager = eventLayoutManager

    }


}



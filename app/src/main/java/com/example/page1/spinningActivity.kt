package com.example.page1

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.page1.BackendService
import com.example.page1.BackendServiceFactory
import com.example.page1.CityDetails
import com.example.page1.adapters.CityDetailsAdapter

class spinningActivity : AppCompatActivity() {

    private lateinit var cityRecyclerView: RecyclerView
    private lateinit var cityAdapter: CityDetailsAdapter
    private lateinit var backendService: BackendService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinning)

        val bookNowButton: Button = findViewById(R.id.cdbookNow2)
        bookNowButton.setOnClickListener {
            // Handle the "Book Now" button click
            val intent = Intent(this@spinningActivity, BookingDialog::class.java)
            startActivity(intent)
        }

        val toolbar: Toolbar = findViewById(R.id.citydetail_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // Enable the back button

        cityRecyclerView = findViewById(R.id.cityRecyclerView)
        cityAdapter = CityDetailsAdapter(emptyList())
        cityRecyclerView.layoutManager = LinearLayoutManager(this)
        cityRecyclerView.adapter = cityAdapter

        backendService = BackendServiceFactory.create()

        fetchData()

    }

    private fun fetchData() {
        val call = backendService.getEvents("", "")

        call.enqueue(object : retrofit2.Callback<List<EventsDataResponse>> {
            override fun onResponse(
                call: retrofit2.Call<List<EventsDataResponse>>,
                response: retrofit2.Response<List<EventsDataResponse>>
            ) {
                if (response.isSuccessful) {
                    val cityDetailsList = response.body()
                    if (cityDetailsList != null) {
                        //cityAdapter = CityDetailsAdapter(cityDetailsList)
                        cityRecyclerView.adapter = cityAdapter
                    }
                } else {
                    // Handle error
                    Toast.makeText(
                        this@spinningActivity,
                        "Error: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<List<EventsDataResponse>>, t: Throwable) {
                // Handle failure
                Toast.makeText(this@spinningActivity, "Error: ${t.message}", Toast.LENGTH_SHORT)
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
}



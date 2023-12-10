package com.example.page1

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.page1.adapters.CityAdapter
import com.example.page1.databinding.ActivityMainBinding

/**class MainActivity2 : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var dropdown: Spinner

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    private  lateinit var navController: NavController

    private fun startNewActivity() {
        // Create an Intent to start the new activity
        val intent = Intent(this, spinningActivity::class.java)

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val myWheelButton: Button = findViewById(R.id.my_wheel_Button)
        myWheelButton.setOnClickListener {

            startNewActivity()
        }
        val actionBar: ActionBar? = actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

            recyclerView = findViewById(R.id.recyclerView)
            dropdown = findViewById(R.id.dropdown)

            // Set up the RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            val adapter = CityAdapter(getCityList(),
                onItemClick = { position ->

                },
                onImageClick = { position ->

                    val intent: Intent = when (position) {
                        0 -> Intent(this@MainActivity2, AthensDetails::class.java)
                        1 -> Intent(this@MainActivity2, RomeDetails::class.java)
                        2 -> Intent(this@MainActivity2, LondonDetails::class.java)
                        3 -> Intent(this@MainActivity2, ParisDetails::class.java)
                        else -> throw IllegalArgumentException("Unknown position clicked")
                    }
                    startActivity(intent)
                })
            recyclerView.adapter = adapter

            // Set up the Spinner
            val spinnerAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
                this,
                R.array.dropdown_options,
                android.R.layout.simple_spinner_item
            )
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dropdown.adapter = spinnerAdapter

            // Set up the item selection listener for the Spinner
            dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View?,
                    position: Int,
                    id: Long
                ) {
                    // Get the selected item
                    val selectedCity: String = parentView?.getItemAtPosition(position).toString()

                    // Launch the appropriate activity based on the selected item
                    when (selectedCity) {
                        "Type of Events" -> {
                            // Do nothing or handle as needed
                        }

                        "Music Events" -> {
                            val intent = Intent(this@MainActivity2, MusicEventActivity::class.java)
                            startActivity(intent)
                        }

                        "Theatres" -> {
                            val intent = Intent(this@MainActivity2, TheatresActivity::class.java)
                            startActivity(intent)
                        }

                        "Festivals" -> {
                            val intent = Intent(this@MainActivity2, festival_activity::class.java)
                            startActivity(intent)
                        }

                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    // Handle case when nothing is selected in the Spinner
                }
            }
        }
    }

        private fun getCityList(): List<City> {

            return listOf(
                City(
                    "Athens",
                    R.drawable.img,
                    "One of the most ancient cities in the world, Athens Greece is famous as the birthplace of democracy.\n" +
                            "         With a history of over 3,000 years,\n" +
                            "         it is the best town for sightseeing. The city took its name after Athena, the Goddess of Wisdom and daughter of Zeus."
                ),
                City(
                    "Rome",
                    R.drawable.img_1,
                    "Rome is often referred to as the City of Seven Hills due to its geographic location, and also as the \"Eternal City\". Rome is generally considered to be the \"cradle of Western civilization and Christian culture\", and the centre of the Catholic Church."
                ),
                City(
                    "London",
                    R.drawable.img_2,
                    "London is the capital of England and the United Kingdom and one of the largest and most important cities in the world. The area was originally settled by early hunter gatherers around 6,000 B.C., and researchers have found evidence of Bronze Age bridges and Iron Age forts near the River Thames.n"
                ),
                City(
                    "Paris",
                    R.drawable.img_3,
                    "Since the 17th century, Paris has been one of the world's major centres of finance, diplomacy, commerce, culture, fashion, gastronomy and many areas. For its leading role in the arts and sciences, as well as its early and extensive system of street lighting, in the 19th century, it became known as the City of Light."
                )

            )
        }*/



import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var dropdown: Spinner
    private lateinit var backendService: BackendService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val myWheelButton: Button = findViewById(R.id.my_wheel_Button)

        // Set an OnClickListener for the button
        myWheelButton.setOnClickListener {
            // Start the AboutUsActivity when the button is clicked
            val intent = Intent(this@MainActivity2, AboutUs::class.java)
            startActivity(intent)
        }


        val toolbar: Toolbar = findViewById(R.id.main2_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {

            setDisplayHomeAsUpEnabled(true)  // Enable the back button

        }

//        val myWheelButton: Button = findViewById(R.id.my_wheel_Button)
//        myWheelButton.setOnClickListener {
//            startNewActivity()
//        }

        val actionBar: ActionBar? = actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.main_cities_recyclerView)
        dropdown = findViewById(R.id.dropdown)

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-snap.cyclic.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        backendService = retrofit.create(BackendService::class.java)

        fetchCityData()

        val spinnerAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.dropdown_options,
            android.R.layout.simple_spinner_item
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdown.adapter = spinnerAdapter

        // Set up the item selection listener for the Spinner
        dropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                val selectedCategory = when(parentView?.getItemAtPosition(position).toString()) {
                    "Music Events" -> "music"
                    "Theatres" -> "theater"
                    "Festivals" -> "festival"
                    else -> ""
                }
                if(selectedCategory.isNotBlank()) {
                    val intent = Intent(this@MainActivity2, EventsActivity::class.java)
                    intent.putExtra("selectedCategory", selectedCategory)
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Handle case when nothing is selected in the Spinner
            }
        }
    }

    private fun startNewActivity() {
        // Create an Intent to start the new activity
        val intent = Intent(this, spinningActivity::class.java)
        startActivity(intent)
    }

    private fun fetchCityData() {
        val call = backendService.getCities()

        call.enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.isSuccessful) {
                    val cityList = response.body()
                    if (cityList != null) {
                        // Update the RecyclerView with the data from the backend
                        updateRecyclerView(cityList)
                    }
                } else {
                    // Handle error
                    Toast.makeText(
                        this@MainActivity2,
                        "Error: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                // Handle failure
                Toast.makeText(this@MainActivity2, "Error: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun updateRecyclerView(cityList: List<City>) {
        val adapter = CityAdapter(
            cityList,
            onItemClick = { _ ->
            },
            onImageClick = { position ->
                val selectedCity = when (position) {
                    0 -> "athens"
                    1 -> "rome"
                    2 -> "london"
                    else -> "paris"
                }
                val intent = Intent(this@MainActivity2, EventsActivity::class.java)
                intent.putExtra("selectedCity", selectedCity)
                startActivity(intent)
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}





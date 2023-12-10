package com.example.page1

/**import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.page1.R

class MoreInfoDialog : DialogFragment() {

    companion object {
        const val EXTRA_EVENT_INFO = "extra_event_info"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_more_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eventInfoTextView: TextView = view.findViewById(R.id.eventInfoTextView)

        val eventInfo = arguments?.getString(EXTRA_EVENT_INFO)
        eventInfo?.let {
            eventInfoTextView.text = it
        }
    }
} */
/**import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.page1.R

class MoreInfoDialog : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_EVENT_INFO = "extra_event_info"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_more_info)


        val toolbar: Toolbar = findViewById(R.id.mi_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {

            setDisplayHomeAsUpEnabled(true)  // Enable the back button

        }

        val eventInfoTextView: TextView = findViewById(R.id.eventInfoTextView)

        val eventInfo = intent.getStringExtra(EXTRA_EVENT_INFO)
        eventInfo?.let {
            eventInfoTextView.text = it
        }
    }
}*/
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**class MoreInfoDialog : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_EVENT_INFO = "extra_event_info"
        const val EXTRA_EVENT_IMAGE_1 = "extra_event_image_1"
        const val EXTRA_EVENT_IMAGE_2 = "extra_event_image_2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_more_info)

        val toolbar: Toolbar = findViewById(R.id.mi_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)  // Enable the back button
        }

        val eventInfoTextView: TextView = findViewById(R.id.eventInfoTextView)
        val imageView1: ImageView = findViewById(R.id.mi_imageView1)
        val imageView2: ImageView = findViewById(R.id.mi_imageView2)
        val bookNowButton: Button = findViewById(R.id.button) // Assuming the ID is button

        val eventInfo = intent.getStringExtra(EXTRA_EVENT_INFO)
        val image1 = intent.getStringExtra(EXTRA_EVENT_IMAGE_1)
        val image2 = intent.getStringExtra(EXTRA_EVENT_IMAGE_2)

        eventInfo?.let {
            eventInfoTextView.text = it
        }

        // Load the first image
        Glide.with(this@MoreInfoDialog).load(image1).into(imageView1)

        // Load the second image
        Glide.with(this@MoreInfoDialog).load(image2).into(imageView2)

        bookNowButton.setOnClickListener {
            showBookingDialog(eventInfo, image1, image2)
        }
    }

    private fun showBookingDialog(eventInfo: String?, image1: String?, image2: String?) {
        val bookingDialog = BookingDialog()

        // Pass the eventData to the BookingDialog
        val args = Bundle()
        args.putString("eventName", eventInfo)
        args.putString("image1", image1)
        args.putString("image2", image2)
        bookingDialog.arguments = args

        // Set the listener to handle the booking confirmation
        bookingDialog.setBookingDialogListener(object : BookingDialog.BookingDialogListener {
            override fun onBookingConfirmed(numTickets: Int, totalPrice: Double) {
                // Handle the booking confirmation, e.g., update your data model or show a message
                // You can access eventData and other relevant data here
            }
        })

        // Show the dialog
        val fragmentManager = supportFragmentManager
        bookingDialog.show(fragmentManager, "BookingDialog")
    }
    private fun makeBookingRequest(eventId: String, numTickets: Int, totalPrice: Double) {
        val bookingRequest = BookingRequest(eventId, numTickets, totalPrice)

        // Create Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://event-snap.cyclic.app/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create BackendService
        val backendService = retrofit.create(BackendService::class.java)

        // Make PUT request
        backendService.bookEvent(eventId, bookingRequest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Booking successful
                    // You may handle the success response here
                } else {
                    // Booking failed
                    // You may handle the failure response here
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Booking failed
                // You may handle the failure response here
            }
        })
    }
} */
class MoreInfoDialog : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_EVENT_INFO = "extra_event_info"
        const val EXTRA_EVENT_IMAGE_1 = "extra_event_image_1"
        const val EXTRA_EVENT_IMAGE_2 = "extra_event_image_2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_more_info)

        val toolbar: Toolbar = findViewById(R.id.mi_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)  // Enable the back button
        }

        val eventInfoTextView: TextView = findViewById(R.id.eventInfoTextView)
        val imageView1: ImageView = findViewById(R.id.mi_imageView1)
        val imageView2: ImageView = findViewById(R.id.mi_imageView2)
        val im1: ImageView = findViewById(R.id.im1)
        val im2: ImageView = findViewById(R.id.im2)

        val eventInfo = intent.getStringExtra(EXTRA_EVENT_INFO)
        val image1 = intent.getStringExtra(EXTRA_EVENT_IMAGE_1)
        val image2 = intent.getStringExtra(EXTRA_EVENT_IMAGE_2)

        eventInfo?.let {
            eventInfoTextView.text = it
        }

        // Load the first image
        Glide.with(this@MoreInfoDialog).load(image1).into(imageView1)

        // Load the second image
        Glide.with(this@MoreInfoDialog).load(image2).into(imageView2)

        // Set OnClickListener for the first image
        im1.setOnClickListener {
            openAboutUsActivity()
        }

        // Set OnClickListener for the second image
        im2.setOnClickListener {
            openAboutUsActivity()
        }
    }

    private fun openAboutUsActivity() {
        val intent = Intent(this, AboutUs::class.java)
        startActivity(intent)
    }
}






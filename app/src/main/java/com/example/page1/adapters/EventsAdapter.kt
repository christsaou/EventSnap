package com.example.page1.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.page1.BookingDialog
import com.example.page1.EventsDataResponse
import com.example.page1.MoreInfoDialog
import com.example.page1.R

/**class EventsAdapter(private val context: Context, private val eventList: List<EventsDataResponse>) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img2)
        val whoTextView: TextView = itemView.findViewById(R.id.who2)
        val whereTextView: TextView = itemView.findViewById(R.id.where2)
        val whenTextView: TextView = itemView.findViewById(R.id.when2)
        val bookNowButton: Button = itemView.findViewById(R.id.bookNow2)
        val moreInfoTextView: TextView = itemView.findViewById(R.id.more_info_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.events_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val eventData = eventList[position]


        val tempImage = "https://images.unsplash.com/photo-1499364615650-ec38552f4f34?q=80&w=1972&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        holder.imageView.load(if(eventData.image1.isNullOrBlank()) tempImage else eventData.image1)
        holder.whoTextView.text = "${eventData.eventTitle}"
        holder.whereTextView.text = "Where: ${eventData.venue}"
        holder.whenTextView.text = "When: ${eventData.eventDate}"

        holder.moreInfoTextView.setOnClickListener {
            showMoreInfoDialog(eventData.description.toString())
        }

        holder.bookNowButton.setOnClickListener {
            showBookingDialog(eventData)
        }
    }

    private fun showBookingDialog(eventData: EventsDataResponse) {
        val bookingDialog = BookingDialog()

        // Pass the eventData to the BookingDialog
        val args = Bundle()
       // args.putString("eventName", eventData.who) // You can pass any necessary data here
        bookingDialog.arguments = args

        // Set the listener to handle the booking confirmation
        bookingDialog.setBookingDialogListener(object : BookingDialog.BookingDialogListener {
            override fun onBookingConfirmed(numTickets: Int, ticketPrice: Double) {
                // Handle the booking confirmation, e.g., update your data model or show a message
                // You can access eventData and other relevant data here
            }
        })

        // Show the dialog
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        bookingDialog.show(fragmentManager, "BookingDialog")
    }

    private fun showMoreInfoDialog(moreInfo: String) {
        val moreInfoDialog = MoreInfoDialog()

        // Pass the moreInfo to the MoreInfoDialog
        val args = Bundle()
        args.putString(MoreInfoDialog.EXTRA_EVENT_INFO, moreInfo)
        moreInfoDialog.arguments = args

        // Show the dialog
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        try {
            moreInfoDialog.show(fragmentManager, "MoreInfoDialog")
        } catch (e: Exception) {
            Log.e("AthensEventAdapter", "Error showing MoreInfoDialog: ${e.message}")
        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
} */




/**class EventsAdapter(private val context: Context, private val eventList: List<EventsDataResponse>) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img2)
        val whoTextView: TextView = itemView.findViewById(R.id.who2)
        val whereTextView: TextView = itemView.findViewById(R.id.where2)
        val whenTextView: TextView = itemView.findViewById(R.id.when2)
        val bookNowButton: Button = itemView.findViewById(R.id.bookNow2)
        val moreInfoTextView: TextView = itemView.findViewById(R.id.more_info_text)
        val priceTextView: TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.events_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventData = eventList[position]

        val tempImage =
            "https://images.unsplash.com/photo-1499364615650-ec38552f4f34?q=80&w=1972&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"

        // Load the first image
        holder.imageView.load(if (eventData.image1.isNullOrBlank()) tempImage else eventData.image1)

        holder.whoTextView.text = "${eventData.eventTitle}"
        holder.whereTextView.text = "Where: ${eventData.venue}"
        holder.whenTextView.text = "When: ${eventData.eventDate}"
        holder.priceTextView.text = "Price:${eventData.price}"

        holder.moreInfoTextView.setOnClickListener {
            showMoreInfoActivity(eventData)
        }

        holder.bookNowButton.setOnClickListener {
            showBookingDialog(eventData)
        }
    }

    private fun showBookingDialog(eventData: EventsDataResponse) {
        val bookingDialog = BookingDialog()

        // Pass the eventData to the BookingDialog
        val args = Bundle()
        args.putString("eventName", eventData.description)
        args.putString("image1", eventData.image1)
        args.putString("image2", eventData.image2)
        bookingDialog.arguments = args

        // Set the listener to handle the booking confirmation
        bookingDialog.setBookingDialogListener(object : BookingDialog.BookingDialogListener {
            override fun onBookingConfirmed(numTickets: Int, ticketPrice: Int) {
                // Handle the booking confirmation, e.g., update your data model or show a message
                // You can access eventData and other relevant data here
            }
        })

        // Show the dialog
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        bookingDialog.show(fragmentManager, "BookingDialog")
    }

    private fun showMoreInfoActivity(eventData: EventsDataResponse) {
        val intent = Intent(context, MoreInfoDialog::class.java)

        // Pass the eventData to the MoreInfoActivity if needed
        intent.putExtra(MoreInfoDialog.EXTRA_EVENT_INFO, eventData.description)
        intent.putExtra(MoreInfoDialog.EXTRA_EVENT_IMAGE_1, eventData.image1)
        intent.putExtra(MoreInfoDialog.EXTRA_EVENT_IMAGE_2, eventData.image2)

        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}*/



class EventsAdapter(private val context: Context, private val eventList: List<EventsDataResponse>) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img2)
        val whoTextView: TextView = itemView.findViewById(R.id.who2)
        val whereTextView: TextView = itemView.findViewById(R.id.where2)
        val whenTextView: TextView = itemView.findViewById(R.id.when2)
        val bookNowButton: Button = itemView.findViewById(R.id.bookNow2)
        val moreInfoTextView: TextView = itemView.findViewById(R.id.more_info_text)
        val priceTextView: TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.events_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventData = eventList[position]

        val tempImage =
            "https://images.unsplash.com/photo-1499364615650-ec38552f4f34?q=80&w=1972&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"

        // Load the first image
        holder.imageView.load(if (eventData.image1.isNullOrBlank()) tempImage else eventData.image1)

        holder.whoTextView.text = "${eventData.eventTitle}"
        holder.whereTextView.text = "Where: ${eventData.venue}"
        holder.whenTextView.text = "When: ${eventData.eventDate}"
        holder.priceTextView.text = "Price:${eventData.price}"

        holder.moreInfoTextView.setOnClickListener {
            showMoreInfoActivity(eventData)
        }

        holder.bookNowButton.setOnClickListener {
            showBookingDialog(eventData)
        }
    }

    private fun showBookingDialog(eventData: EventsDataResponse) {
        val bookingDialog = BookingDialog()

        // Pass the eventData to the BookingDialog
        bookingDialog.setEventData(eventData)

        // Set the listener to handle the booking confirmation
        bookingDialog.setBookingDialogListener(object : BookingDialog.BookingDialogListener {
            override fun onBookingConfirmed(numTickets: Int, totalPrice: Double) {
                // Handle the booking confirmation, e.g., update your data model or show a message
                // You can access eventData and other relevant data here
            }
        })

        // Show the dialog
        val fragmentManager = (context as AppCompatActivity).supportFragmentManager
        bookingDialog.show(fragmentManager, "BookingDialog")
    }

    private fun showMoreInfoActivity(eventData: EventsDataResponse) {
        val intent = Intent(context, MoreInfoDialog::class.java)

        // Pass the eventData to the MoreInfoActivity if needed
        intent.putExtra(MoreInfoDialog.EXTRA_EVENT_INFO, eventData.description)
        intent.putExtra(MoreInfoDialog.EXTRA_EVENT_IMAGE_1, eventData.image1)
        intent.putExtra(MoreInfoDialog.EXTRA_EVENT_IMAGE_2, eventData.image2)

        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}














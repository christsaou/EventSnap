package com.example.page1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.page1.CityDetails
import com.example.page1.R
import android.widget.Button

class CityDetailsAdapter(private val cityDetailsList: List<CityDetails>) :
    RecyclerView.Adapter<CityDetailsAdapter.CityDetailsViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityDetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_details_recycler_view, parent, false)
        return CityDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityDetailsViewHolder, position: Int) {
        val cityDetails = cityDetailsList[position]
        holder.bind(cityDetails)

    }

    override fun getItemCount(): Int {
        return cityDetailsList.size
    }

    inner class CityDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val eventTitleTextView: TextView = itemView.findViewById(R.id.eventTitleTextView)
        private val locationTextView: TextView = itemView.findViewById(R.id.locationTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private val venueTextView: TextView = itemView.findViewById(R.id.venueTextView)
        private val eventTypeTextView: TextView = itemView.findViewById(R.id.eventTypeTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        private val eventDateTextView: TextView = itemView.findViewById(R.id.eventDateTextView)

        private val image1View: ImageView = itemView.findViewById(R.id.image1View)
        private val image2View: ImageView = itemView.findViewById(R.id.image2View)
        private val image3View: ImageView = itemView.findViewById(R.id.image3View)
        private val image4View: ImageView = itemView.findViewById(R.id.image4View)

        fun bind(cityDetails: CityDetails) {
            // Bind your data to the views here
            eventTitleTextView.text = cityDetails.eventTitle
            locationTextView.text = cityDetails.location
            priceTextView.text = cityDetails.price.toString()
            venueTextView.text = cityDetails.venue
            eventTypeTextView.text = cityDetails.eventType
            descriptionTextView.text = cityDetails.description
            eventDateTextView.text = cityDetails.eventDate

            // Example of loading an image using Glide
            Glide.with(itemView.context).load(cityDetails.image1).into(image1View)
            Glide.with(itemView.context).load(cityDetails.image2).into(image2View)
            Glide.with(itemView.context).load(cityDetails.image3).into(image3View)
            Glide.with(itemView.context).load(cityDetails.image4).into(image4View)
        }
    }
}







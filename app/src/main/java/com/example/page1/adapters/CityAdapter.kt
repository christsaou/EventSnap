package com.example.page1.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.page1.R
import com.example.page1.City
import java.io.IOException
import java.net.URL
import com.bumptech.glide.Glide
import java.util.Locale


/**class CityAdapter(
    private val cityList: List<City>,
    private val onItemClick: (Int) -> Unit,
    private val onImageClick: (Int) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view, onItemClick, onImageClick)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cityList[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    class CityViewHolder(
        itemView: View,
        onItemClick: (Int) -> Unit,
        onImageClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)
        private val cityImageView: ImageView = itemView.findViewById(R.id.cityImageView)
        private val cityInfoTextView: TextView = itemView.findViewById(R.id.cityInfoTextView)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }

            cityImageView.setOnClickListener {
                onImageClick(adapterPosition)
            }
        }

        fun bind(city: City) {
            cityNameTextView.text = city.name
            cityImageView.setImageResource(city.imageResId)
            cityInfoTextView.text = city.info
        }
    }
} */
class CityAdapter(
    private val cityList: List<City>,
    private val onItemClick: (Int) -> Unit,
    private val onImageClick: (Int) -> Unit
) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view, onItemClick, onImageClick)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cityList[position]
        holder.bind(city)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    class CityViewHolder(
        itemView: View,
        onItemClick: (Int) -> Unit,
        onImageClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)
        private val cityImageView: ImageView = itemView.findViewById(R.id.cityImageView)
        private val cityInfoTextView: TextView = itemView.findViewById(R.id.cityInfoTextView)

        init {
            itemView.rootView.setOnClickListener {
                onItemClick(adapterPosition)
            }

            cityImageView.setOnClickListener {
                onImageClick(adapterPosition)
            }
        }

        fun bind(city: City) {
            cityNameTextView.text = city.name.capitalize(Locale.ROOT)
            cityInfoTextView.text = city.info

            // Load the image using Glide
            Glide.with(itemView.context)
                .load(city.image)
                .into(cityImageView)
        }
    }
}
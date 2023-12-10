package com.example.page1.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.page1.R

class SelectedCityImagesAdapter(
    private val context: Context,
    private val itemList: List<Int>,
    private val onItemClick: (position: Int) -> Unit
) : RecyclerView.Adapter<SelectedCityImagesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.athens_recyvcle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageResource = itemList[position]
        holder.imageView.setImageResource(imageResource)


        holder.itemView.setOnClickListener {
            onItemClick.invoke(position)
            Log.d("AthensAdapter", "Item clicked at position $position with resource ID $imageResource")
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}

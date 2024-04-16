package com.example.makemytripapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class InterestData(val text: String, val imageResId: Int)

class InterestAdapter(private val dataSet: List<InterestData>) :
    RecyclerView.Adapter<InterestAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val circleImage: ImageView = view.findViewById(R.id.circle_image)
        val circleText: TextView = view.findViewById(R.id.circle_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.discover_places, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        holder.circleText.text = item.text
        holder.circleImage.setImageResource(item.imageResId)
    }

    override fun getItemCount() = dataSet.size
}



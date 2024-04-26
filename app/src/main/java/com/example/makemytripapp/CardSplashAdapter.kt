package com.example.makemytripapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class CardsSplashAdapter(private val dataSet: List<CardItem>) :
    RecyclerView.Adapter<CardsSplashAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.card_image_curved)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_splash_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardItem = dataSet[position]
        holder.imageView.setImageResource(cardItem.imageResourceId)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

data class CardItem(val imageResourceId: Int)

package com.example.makemytripapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TripAdapter(private val mContext: Context, private val mTripList: List<Trip>) :
    RecyclerView.Adapter<TripAdapter.TripViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view =
            LayoutInflater.from(mContext).inflate(R.layout.trip_cards, parent, false)
        return TripViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip: Trip = mTripList[position]
        holder.tripImageView.setImageResource(trip.imageResource)
        holder.tripTextView.text = trip.tripText
    }

    override fun getItemCount(): Int {
        return mTripList.size
    }

    inner class TripViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tripImageView: ImageView = itemView.findViewById(R.id.trips_imagecards)
        var tripTextView: TextView = itemView.findViewById(R.id.trip_textcard)
    }
}

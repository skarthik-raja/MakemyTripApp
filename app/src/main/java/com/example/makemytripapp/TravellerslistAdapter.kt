package com.example.makemytripapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TravellerslistAdapter(private val dataList: List<String>) : RecyclerView.Adapter<TravellerslistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val smallCardText: TextView = itemView.findViewById(R.id.small_card_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.travellers_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.smallCardText.text = data
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

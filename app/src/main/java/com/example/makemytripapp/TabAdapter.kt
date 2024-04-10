package com.example.makemytripapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TabAdapter(private val context: Context) : RecyclerView.Adapter<TabAdapter.ViewHolder>() {

    private var data: List<YourDataModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        // Set text
        holder.textView.text = item.text
        // Set image
        holder.imageView.setImageResource(item.imageResId)
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: List<YourDataModel>) {
        data = newData
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.card_textview)
        val imageView: ImageView = itemView.findViewById(R.id.card_imageview)
    }
}

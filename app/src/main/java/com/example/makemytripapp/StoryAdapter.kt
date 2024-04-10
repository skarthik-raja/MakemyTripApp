package com.example.makemytripapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoryAdapter(val list: List<Pair<Int, String>>): RecyclerView.Adapter<StoryAdapter.MyView>() {

    inner class MyView(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView:ImageView=itemView.findViewById(R.id.imagecards)
        val textView: TextView = itemView.findViewById(R.id.text_below_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        return MyView(LayoutInflater.from(parent.context).inflate(R.layout.story_item,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.imageView.setImageResource(list[position].first)
        holder.textView.text = list[position].second
    }
}


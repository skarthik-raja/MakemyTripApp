package com.example.makemytripapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CustomAdapter(
    private val itemModel: ArrayList<Model>,
    private val fragment: Fragment
) : BaseAdapter() {
    private val context: Context = fragment.requireContext()
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return itemModel.size
    }

    override fun getItem(position: Int): Any {
        return itemModel[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertedView = convertView
        if (convertedView == null) {
            convertedView = layoutInflater.inflate(R.layout.row_items, parent, false)
        }
        val tvImageName = convertedView?.findViewById<TextView>(R.id.gridtext_1)
        val imageView = convertedView?.findViewById<ImageView>(R.id.gridimage_1)

        tvImageName?.text = itemModel[position].text
        imageView?.setImageResource(itemModel[position].image!!)

        return convertedView!!
    }
}

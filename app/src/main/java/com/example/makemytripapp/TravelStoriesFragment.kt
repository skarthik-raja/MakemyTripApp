package com.example.makemytripapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TravelStoriesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var dataList: MutableList<MyData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_travel_stories, container, false)

        recyclerView = view.findViewById(R.id.travel_neweststories)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        dataList = mutableListOf(
            MyData(
                R.drawable.travel5,
                "Text 1 for Image 1",
                "Text 2 for Image 1",
                "Text 3 for Image 1"
            ),
            MyData(
                R.drawable.travel1,
                "Text 1 for Image 2",
                "Text 2 for Image 2",
                "Text 3 for Image 2"
            ),
        )

        adapter = MyAdapter(requireContext(), dataList)
        recyclerView.adapter = adapter

        return view
    }
}
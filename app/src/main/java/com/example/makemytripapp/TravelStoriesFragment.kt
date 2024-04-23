package com.example.makemytripapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import placesstoryAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class TravelStoriesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: placesstoryAdapter
    private lateinit var dataList: MutableList<MyPlaceStoryData>

    private lateinit var recyclerViewstory: RecyclerView
    private lateinit var adapterstory: StoriesAdapter
    private val storiesList = ArrayList<StoryModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_travel_stories, container, false)


        recyclerView = view.findViewById(R.id.travel_neweststories)

        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        dataList = mutableListOf(
            MyPlaceStoryData(
                R.drawable.dubai,
                "Dubai",
                "Safvanbhai",
                "13 mins ago"
            ),
            MyPlaceStoryData(
                R.drawable.mahabalipuram,
                "Mahabalipuram",
                "manali",
                "13h ago"
            ),
            MyPlaceStoryData(
                R.drawable.marina,
                "Marina",
                "Beach",
                "1h ago"
            ),
            MyPlaceStoryData(
                R.drawable.radisonhotel,
                "Nepal",
                "Sachin",
                "20h ago"
            )
        )

        adapter = placesstoryAdapter(requireContext(), dataList)
        recyclerView.adapter = adapter


        val recyclerViewtravel: RecyclerView = view.findViewById(R.id.travellers_items)
        recyclerViewtravel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val dataList = listOf("All", "Adventure", "Beach","Food","Relaxation","Hills & Mountains","Wildlife","Trek")
        val adapter = TravellerslistAdapter(dataList)
        recyclerViewtravel.adapter = adapter

        recyclerViewstory = view.findViewById(R.id.reels_stories)
        recyclerViewstory.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        storiesList.add(StoryModel(R.drawable.travel, "Fabulous Trip"))
        storiesList.add(StoryModel(R.drawable.international, "Explore the world"))
        storiesList.add(StoryModel(R.drawable.chennaitravel,"M.G.R"))

        adapterstory = StoriesAdapter(requireActivity(), storiesList)
        recyclerViewstory.adapter = adapterstory

        return view
    }
}
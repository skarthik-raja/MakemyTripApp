package com.example.makemytripapp

import MediaAdapter
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import placesstoryAdapter

class TravelStoriesFragment : Fragment(), MediaAdapter.OnVideoClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: placesstoryAdapter
    private lateinit var dataList: MutableList<MyPlaceStoryData>

    private lateinit var recyclerViewstory: RecyclerView
    private lateinit var adapterstory: MediaAdapter
    private val storiesList = ArrayList<MediaModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_travel_stories, container, false)

        recyclerView = view.findViewById(R.id.travel_neweststories)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        dataList = mutableListOf(
            MyPlaceStoryData(R.drawable.dubai, "Dubai", "Safvanbhai", "13 mins ago"),
            MyPlaceStoryData(R.drawable.mahabalipuram, "Mahabalipuram", "manali", "13h ago"),
            MyPlaceStoryData(R.drawable.marina, "Marina", "Beach", "1h ago"),
            MyPlaceStoryData(R.drawable.radisonhotel, "Nepal", "Sachin", "20h ago")
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

        storiesList.add(MediaModel(R.drawable.travel, 0, "Fabulous Trip"))
        storiesList.add(MediaModel(R.drawable.international, 0, "Explore the world"))
        storiesList.add(MediaModel(R.drawable.chennaitravel, 0, "M.G.R"))
        storiesList.add(MediaModel(R.raw.falls, 1, "water falls", R.raw.falls))
        storiesList.add(MediaModel(R.raw.roadview,1,"roadview",R.raw.roadview))
        storiesList.add(MediaModel(R.raw.swimming,1,"swimming",R.raw.swimming))

        adapterstory = MediaAdapter(storiesList)
        recyclerViewstory.adapter = adapterstory
        adapterstory.setOnVideoClickListener(this)

        return view
    }

    override fun onVideoClick(position: Int) {
        val mediaModel = storiesList[position]
        if (mediaModel.mediaType == 1) {
            stopAllVideosExcept(position)
            mediaModel.isPlaying = !mediaModel.isPlaying
            adapterstory.notifyDataSetChanged()
        }
    }

    private fun stopAllVideosExcept(positionToExclude: Int) {
        // Iterate through the storiesList and stop all playing videos except the one at positionToExclude
        for ((index, story) in storiesList.withIndex()) {
            if (story.mediaType == 1 && story.isPlaying && index != positionToExclude) {
                // Stop the video playback
                story.isPlaying = false
            }
        }
    }
}

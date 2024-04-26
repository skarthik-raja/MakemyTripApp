package com.example.makemytripapp

import PlaceAdapter
import VideoAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class DiscoverFragment : Fragment() {

    private lateinit var tripAdapter: TripAdapter
    private lateinit var videoRecyclerView: RecyclerView
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_discover, container, false)

        val placeRecyclerView: RecyclerView = view.findViewById(R.id.place_recyclerview)

        val adapter = PlaceAdapter(requireContext(), getDummyPlaces())
        placeRecyclerView.adapter = adapter

        placeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val discoverrecycle: RecyclerView = view.findViewById(R.id.discover_recycle)

        val adapter3 = InterestAdapter(getInterestplaces())
        discoverrecycle.adapter = adapter3

        discoverrecycle.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val recyclerViewtrip: RecyclerView = view.findViewById(R.id.tripcards)
        recyclerViewtrip.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        tripAdapter = TripAdapter(requireContext(), getTripList())

        recyclerViewtrip.adapter = tripAdapter

        videoRecyclerView = view.findViewById(R.id.videocards)
        videoRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val videoUris = getVideoUris()

        videoAdapter = VideoAdapter(requireContext(), videoUris) // Create an instance of VideoAdapter and pass the video URIs
        videoRecyclerView.adapter = videoAdapter

        videoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val firstVisiblePosition = layoutManager.findFirstVisibleItemPosition()
                    val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()

                    for (i in 0 until videoAdapter.itemCount) {
                        if (i < firstVisiblePosition || i > lastVisiblePosition) {
                            videoAdapter.stopPlayback()
                        }
                    }

                    val visibleVideoPosition = layoutManager.findFirstCompletelyVisibleItemPosition()
                    if (visibleVideoPosition != RecyclerView.NO_POSITION) {
                        videoAdapter.playVideo(visibleVideoPosition)
                    }
                }
            }
        })
        videoAdapter.playVideo(0) // Assuming your first video is at position 0

        return view
    }

    private fun getDummyPlaces(): List<Places> {
        return listOf(
            Places("Marina", R.drawable.marina),
            Places("Central Station", R.drawable.central),
            Places("Temple", R.drawable.temple_chennai),
            Places("M.G.R",R.drawable.chennaitravel)
        )
    }

    private fun getInterestplaces(): List<InterestData> {
        return listOf(
            InterestData("Mountain", R.drawable.marina),
            InterestData("Beach", R.drawable.central),
            InterestData("Romantic", R.drawable.temple_chennai),
            InterestData("International",R.drawable.chennaitravel)
        )
    }

    private fun getTripList(): List<Trip> {
        return listOf(
            Trip(R.drawable.travel, "Friendstrip"),
            Trip(R.drawable.romantic, "Romantictrip"),
            Trip(R.drawable.travel1, "Familytrip"),
            Trip(R.drawable.travel5,"Honeymoontrip"),
            Trip(R.drawable.weekend,"Weekendtrip")
        )
    }

    private fun getVideoUris(): List<String> {
        return listOf(
            "android.resource://com.example.makemytripapp/raw/falls",
            "android.resource://com.example.makemytripapp/raw/roadview",
            "android.resource://com.example.makemytripapp/raw/riverfalls",
            "android.resource://com.example.makemytripapp/raw/swimming"
        )
    }

}
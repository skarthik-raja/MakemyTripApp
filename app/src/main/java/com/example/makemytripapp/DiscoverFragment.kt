package com.example.makemytripapp

import PlaceAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DiscoverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_discover, container, false)

        val placeRecyclerView: RecyclerView = view.findViewById(R.id.place_recyclerview)

        val adapter = PlaceAdapter(requireContext(), getDummyPlaces())
        placeRecyclerView.adapter = adapter

        placeRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

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
}
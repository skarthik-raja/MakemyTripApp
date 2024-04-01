package com.example.makemytripapp

import RecyclerAdapter
import RecyclerData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Home_Fragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var dropdownIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_, container, false)
        recyclerView = view.findViewById(R.id.rv_design)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)

        recyclerAdapter = RecyclerAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter
        recyclerAdapter.updateDataset(getAdditionalItems())
        dropdownIcon = view.findViewById(R.id.drop_icon)
        val isExpanded = false

        val recyclerView: RecyclerView = view.findViewById(R.id.text_recyclerview)
        val adapter = TextAdapter(getDataList())
        recyclerView.adapter = adapter
        val horizontalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val verticalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = horizontalLayoutManager

        val viewAllTextView: TextView = view.findViewById(R.id.rightTextView)
        viewAllTextView.setOnClickListener {
            recyclerView.layoutManager = verticalLayoutManager
            adapter.notifyDataSetChanged()
        }

        val stayRecyclerView: RecyclerView = view.findViewById(R.id.recycler_view1)
        val stayAdapter = StayAdapter(getStayDataList())
        stayRecyclerView.adapter = stayAdapter
        val stayLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        stayRecyclerView.layoutManager = stayLayoutManager


        dropdownIcon.setOnClickListener {
            Log.d("HomeFragment","Button is clicked")
            if(isExpanded){
                recyclerAdapter.updateDataset(getAdditionalItems())
                dropdownIcon.visibility=View.GONE
            }else{
                recyclerAdapter.updateDataset(getInitialItems())
                dropdownIcon.visibility=View.GONE

            }
        }

        return view
    }

    private fun getInitialItems(): List<RecyclerData> {
        return listOf(
            RecyclerData("Car", R.drawable.car, false, true),
            RecyclerData("Holidays", R.drawable.holidays, false, true),
            RecyclerData("House", R.drawable.house, false, true),
            RecyclerData("Taxi", R.drawable.taxi, false, true),
            RecyclerData("Car", R.drawable.car, false, true),
            RecyclerData("Holidays", R.drawable.holidays, false, true),
            RecyclerData("House", R.drawable.house, false, true),
            RecyclerData("Taxi", R.drawable.taxi, false, true),
            RecyclerData("Car", R.drawable.car, false, true),
            RecyclerData("Holidays", R.drawable.holidays, false, true),
            RecyclerData("House", R.drawable.house, false, true),
            RecyclerData("Taxi", R.drawable.taxi, false, true)
        )
    }

    private fun getAdditionalItems(): List<RecyclerData> {
        return listOf(
            RecyclerData("Car", R.drawable.car, false, true),
            RecyclerData("HomeStays & villas", R.drawable.house, false, true),
            RecyclerData("Outstation cabs", R.drawable.taxi, false, true),
            RecyclerData("Forex Card & Currency", R.drawable.card, false, true),
            RecyclerData("Gift Cards", R.drawable.gift, false, true),
            RecyclerData("Hourly Stays", R.drawable.hourlystays, false, true),
            RecyclerData("Nearby Staycations", R.drawable.bags, false, true),
            RecyclerData("Travel Insurance", R.drawable.travelinsurance, false, true),
            RecyclerData("Flight Status", R.drawable.car, false, false),
            RecyclerData("Holidays", R.drawable.holidays, false, false),
            RecyclerData("House", R.drawable.house, false, false),
            RecyclerData("Taxi", R.drawable.taxi, false, false)
        )
    }

    private fun getDataList(): List<Pair<String, Int>> {
        return listOf(
            "Trending" to R.drawable.poster1,
            "Hotels" to R.drawable.hotelimages,
            "Flights" to R.drawable.flightimages,
            "Rails" to R.drawable.trainimages,
            "Cabs" to R.drawable.cabsimages,
            "Bus" to R.drawable.busimages
        )
    }

    private fun getStayDataList(): List<StaySite> {
        return listOf(
            StaySite("Vivanta Surajikund,    11,151 " +
                    "NCR             Per night" +
                    "Faridabad ", R.drawable.hotelvivanta),
            StaySite("Taj Palace,New           14,751" +
                    "Delhi           per night" +
                    "Delhi", R.drawable.tajhotel),
            StaySite("Radisson Hotel      4,458" +
                    "Agra          per night" +
                    "Agra", R.drawable.radisonhotel),
        )
    }

}
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Home_Fragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

//    private lateinit var horizontalRecyclerView: RecyclerView
//    private lateinit var horizontalAdapter: YourHorizontalAdapter
    private var dataList = mutableListOf<RecyclerData>()
    private lateinit var dropdownIcon: ImageView
    private lateinit var dropupIcon: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_, container, false)
        recyclerView = view.findViewById(R.id.rv_design)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)

//        dataList.addAll(getInitialItems())

        recyclerAdapter = RecyclerAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter
        recyclerAdapter.updateDataset(getAdditionalItems())
        dropdownIcon = view.findViewById(R.id.drop_icon)
        val isExpanded = false
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
//        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView)
//        horizontalAdapter = YourHorizontalAdapter(getTransportItemList())
//        horizontalRecyclerView.adapter = horizontalAdapter
//        horizontalRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

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
            RecyclerData("Holidays", R.drawable.holidays, false, true),
            RecyclerData("House", R.drawable.house, false, true),
            RecyclerData("Taxi", R.drawable.taxi, false, true),
            RecyclerData("Car", R.drawable.car, false, true),
            RecyclerData("Holidays", R.drawable.holidays, false, true),
            RecyclerData("House", R.drawable.house, false, true),
            RecyclerData("Taxi", R.drawable.taxi, false, true),
            RecyclerData("Car", R.drawable.car, false, false),
            RecyclerData("Holidays", R.drawable.holidays, false, false),
            RecyclerData("House", R.drawable.house, false, false),
            RecyclerData("Taxi", R.drawable.taxi, false, false)
        )
    }

//    private fun getTransportItemList(): List<TransportItem> {
//        val itemList = mutableListOf<TransportItem>()
//        itemList.add(TransportItem(R.drawable.house, "House"))
//        itemList.add(TransportItem(R.drawable.car, "Car"))
//        itemList.add(TransportItem(R.drawable.trains, "Train"))
//        itemList.add(TransportItem(R.drawable.airplane_svgrepo_com__2_,"FLights"))
//        return itemList
//    }
}
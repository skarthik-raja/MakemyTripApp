package com.example.makemytripapp

import ImageAdapter
import RecyclerAdapter
import RecyclerAdapter1
import RecyclerData
import TextAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.tabs.TabLayout


class Home_Fragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var dropdownIcon: ImageView
    private lateinit var tabLayout1: TabLayout
    private lateinit var recyclerView3:RecyclerView
    private lateinit var recyclerView4:RecyclerView
    private lateinit var adapter1: TabAdapter
    private lateinit var adapter3:CardViewAdapter

    private lateinit var recyclerview5:RecyclerView
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var list: ArrayList<Int>

    private lateinit var exclusiveAdapter:ExclisveImageAdapter
    private lateinit var imagesList: MutableList<Int>
    private lateinit var dataList1: List<Pair<String, Int>> // Data list for initial images



    lateinit var imageAdapter: ImageAdapter


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

        recyclerView = view.findViewById(R.id.recyclerViewcard)
        tabLayout1 = view.findViewById(R.id.tabLayout1)

        adapter1 = TabAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter1

        setupTabLayout()

        recyclerView3 = view.findViewById(R.id.image_recyclerview)
        recyclerView3.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val exclusiveImagesList = mutableListOf(
            R.drawable.amazon,
            R.drawable.hdfc,
            R.drawable.samsung
        )

        exclusiveAdapter = ExclisveImageAdapter(requireContext(), exclusiveImagesList)
        recyclerView3.adapter = exclusiveAdapter

        recyclerView4 = view.findViewById(R.id.card_recycler)
        recyclerView4.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val dataList = listOf(
            CardData(R.drawable.thepark, "The Park Chennai", "gangai Karai Puram ★ ★ ★ ★ ★", "Per Night ₹10,000"),
            CardData(R.drawable.gingerhotel, "Ginger Chennai OMR", "OMR chennai★ ★ ★ ★ ", "Per Night ₹15,000"),
            CardData(R.drawable.holidayinn, "HolidayInn", "Omr Road Thoraipakkam★ ★ ★  ", "Per Day ₹20,000")
        )

        adapter3 = CardViewAdapter(dataList)
        recyclerView4.adapter = adapter3

        recyclerview5 = view.findViewById(R.id.storycards)
        recyclerview5.setHasFixedSize(true)

        val list = ArrayList<Pair<Int, String>>()
        list.add(Pair(R.drawable.chennaitravel, "South India Chennai"))
        list.add(Pair(R.drawable.travel1, "Photography"))
        list.add(Pair(R.drawable.travel2, "Travel Journey is going to Start"))
        list.add(Pair(R.drawable.travel3, "Explore the world"))
        list.add(Pair(R.drawable.travel4, "Banglore India"))
        list.add(Pair(R.drawable.travel5, "Singapore"))
        list.add(Pair(R.drawable.bangkok, "Bangkok"))

        storyAdapter = StoryAdapter(list)
        recyclerview5.adapter = storyAdapter
        recyclerview5.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val textRecyclerView: RecyclerView = view.findViewById(R.id.textview_recycler)
        val textAdapter = TextAdapter(getTextDataList()) { text ->
            loadImages(text)
        }
        textRecyclerView.adapter = textAdapter

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        textRecyclerView.layoutManager = layoutManager

        val imageRecyclerView: RecyclerView = view.findViewById(R.id.text_recyclerview)
        dataList1 = getDataList()
        val initialImageList = dataList1.map { it.second }
        imageAdapter = ImageAdapter(initialImageList)
        imageRecyclerView.adapter = imageAdapter
        val horizontalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        imageRecyclerView.layoutManager = horizontalLayoutManager

        val stayRecyclerView: RecyclerView = view.findViewById(R.id.recycler_view1)
        val stayAdapter = StayAdapter(getStayDataList())
        stayRecyclerView.adapter = stayAdapter
        val stayLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        stayRecyclerView.layoutManager = stayLayoutManager

        val leftArrow = view.findViewById<ImageView>(R.id.left_arrow)
        val rightArrow = view.findViewById<ImageView>(R.id.right_arrow)

        leftArrow.setOnClickListener {
            val layoutManager = stayRecyclerView.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(layoutManager.findFirstVisibleItemPosition() - 1, 0)
        }

        rightArrow.setOnClickListener {
            val layoutManager = stayRecyclerView.layoutManager as LinearLayoutManager
            layoutManager.scrollToPositionWithOffset(layoutManager.findLastVisibleItemPosition() + 1, 0)
        }

        val verticalLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val viewAllTextView: TextView = view.findViewById(R.id.rightTextView)
        viewAllTextView.setOnClickListener {
            textRecyclerView.layoutManager = verticalLayoutManager
            textAdapter.notifyDataSetChanged()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.grid_recyclerview)
        val layoutManager1 = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager1

        val imageList = listOf(
            R.drawable.wynadhamhotels,
            R.drawable.thefernhotels,
            R.drawable.indehotels,
            R.drawable.clarkshotels
        )

        val adapter = GridAdapter(requireContext(), imageList)
        recyclerView.adapter = adapter

        var isExpanded = false
        dropdownIcon.setOnClickListener {
            Log.d("HomeFragment", "Button is clicked")
            isExpanded = !isExpanded
            if (isExpanded) {
                recyclerAdapter.updateDataset(getAdditionalItems())
                dropdownIcon.visibility=View.VISIBLE
            } else {
                recyclerAdapter.updateDataset(getInitialItems())
                dropdownIcon.visibility = View.VISIBLE
            }
        }

        return view
    }


    private fun setupTabLayout() {
        tabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        0 -> adapter1.setData(getAnnouncementItems())
                        1 -> adapter1.setData(getTravelNewsItems())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        tabLayout1.addTab(tabLayout1.newTab().setText("Announcement"))
        tabLayout1.addTab(tabLayout1.newTab().setText("Travel News"))
    }
    private fun loadImages(text: String) {
        val images = when (text) {
            "Hotels" -> dataList1.filter { it.first == "Hotels" }.map { it.second }
            "Flights" -> dataList1.filter { it.first == "Flights" }.map { it.second }
            "Bus" -> dataList1.filter { it.first == "Bus" }.map { it.second }
            "Cabs" -> dataList1.filter { it.first == "Cabs" }.map { it.second }
            "Rails" -> dataList1.filter { it.first == "Rails" }.map { it.second }
            "Trending" -> dataList1.filter { it.first == "Trending" }.map { it.second }
            "Holiday" -> dataList1.filter { it.first == "Holiday" }.map { it.second }
            else -> emptyList()
        }
        imageAdapter.setData(images)
    }


    private fun getAnnouncementItems(): List<YourDataModel> {
        return listOf(
            YourDataModel("For Business class Bookings: explore our dedicated business class pages & pick the best flight", R.drawable.vipbusiness),
            YourDataModel("Book Flights with voice chat! Code:MMTMYRA will be auto-applied for EXTRA discounts!", R.drawable.robotchat),
            YourDataModel("Finding Indian Food just got : Use newly launched filters to find indian food during International trade",R.drawable.foodindian)
        )
    }

    private fun getTravelNewsItems(): List<YourDataModel> {
        return listOf(
            YourDataModel("International Flights Now Call 0124-4628747 for booking assistance", R.drawable.airplane_svgrepo_com__2_),
            YourDataModel("Wish to travel Abroad? Tap here to check the international destinations open for indians now", R.drawable.airplane_svgrepo_com__2_),
            YourDataModel("By many Indian states, check revised RT-PCR guidelines for travel",R.drawable.flaticon)
            // Add more items as needed
        )
    }
    private fun getInitialItems(): List<RecyclerData> {
        return listOf(
            RecyclerData("Cab", R.drawable.car, false, true),
            RecyclerData("HomeStays", R.drawable.house, false, true),
            RecyclerData("Outstation  cabs", R.drawable.taxi, false, true),
            RecyclerData("ForexCard& Currency", R.drawable.card, false, true),
            RecyclerData("GiftCards", R.drawable.gift, false, true),
            RecyclerData("HourlyStays", R.drawable.hourlystays, false, true),
            RecyclerData("NearbyStaycations", R.drawable.bags, false, true),
            RecyclerData("Travel    Insurance", R.drawable.travelinsurance, false, true),
            RecyclerData("FlightStatus", R.drawable.flight_ticket_svgrepo_com, false, true),
            RecyclerData("Holidays", R.drawable.holidays, false, true),
            RecyclerData("House", R.drawable.house, false, true),
            RecyclerData("Taxi", R.drawable.taxi, false, true)
        )
    }

    private fun getAdditionalItems(): List<RecyclerData> {
        return listOf(
            RecyclerData("Cab", R.drawable.car, false, true),
            RecyclerData("HomeStays", R.drawable.house, false, true),
            RecyclerData("Outstation  cabs", R.drawable.taxi, false, true),
            RecyclerData("ForexCard& Currency", R.drawable.card, false, true),
            RecyclerData("GiftCards", R.drawable.gift, false, true),
            RecyclerData("HourlyStays", R.drawable.hourlystays, false, true),
            RecyclerData("NearbyStaycations", R.drawable.bags, false, true),
            RecyclerData("Travel   Insurance", R.drawable.travelinsurance, false, true),
            RecyclerData("FlightStatus", R.drawable.flight_ticket_svgrepo_com, false, false),
            RecyclerData("Holidays", R.drawable.holidays, false, false),
            RecyclerData("House", R.drawable.house, false, false),
            RecyclerData("Taxi", R.drawable.taxi, false, false)
        )
    }

    private fun getDataList(): List<Pair<String, Int>> {
        return listOf(
            "Trending" to R.drawable.trendingoffer,
            "Hotels" to R.drawable.hotelimages,
            "Flights" to R.drawable.flightimages,
            "Rails" to R.drawable.trainimages,
            "Cabs" to R.drawable.cabsimages,
            "Bus" to R.drawable.busimages,
            "Holiday" to R.drawable.travel
        )
    }

    private fun getStayDataList(): List<StaySite> {
        return listOf(
            StaySite("Vivanta Surajikund,       ₹11,151   " +
                    "NCR   Per night   " +
                    "        Faridabad ", R.drawable.hotelvivanta),
            StaySite("Taj Palace,New,           ₹14,751 " +
                    "Delhi    per night   " +
                    "         Delhi", R.drawable.tajhotel),
            StaySite("Radisson Hotel,           ₹4,458    " +
                    "Agra    per night  " +
                    "         Agra ", R.drawable.radisonhotel),
        )
    }

    private fun getTextDataList(): List<String> {
        return listOf(
            "Hotels",
            "Flights",
            "Bus",
            "Cabs",
            "Rails",
            "Trending",
            "Holiday"

        )
    }

}
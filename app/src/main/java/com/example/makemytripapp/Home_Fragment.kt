package com.example.makemytripapp

import ImageAdapter
import RecyclerAdapter
import RecyclerData
import TextAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.firestore.FirebaseFirestore

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

    private lateinit var exclusiveAdapter:ExclisveImageAdapter
    //    private lateinit var imagesList: MutableList<Int>
    private lateinit var dataList1: List<Pair<String, Int>> // Data list for initial images
    lateinit var imageAdapter: ImageAdapter
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_, container, false)

        firestore = FirebaseFirestore.getInstance()
        setupViews(view)
        setupRecyclerView()
        retrieveData()
        setupListeners(view)

        recyclerView = view.findViewById(R.id.rv_design)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerAdapter = RecyclerAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter

        dropdownIcon = view.findViewById(R.id.drop_icon)
        dropdownIcon.visibility = View.GONE

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

        return view
    }

    private fun setupViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_design)
        dropdownIcon = view.findViewById(R.id.drop_icon)
        firestore = FirebaseFirestore.getInstance()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        recyclerAdapter = RecyclerAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter
    }

    private fun retrieveData() {
        firestore.collection("HomeData")
            .get()
            .addOnSuccessListener { result ->
                val dataset = mutableListOf<RecyclerData>()

                for (document in result) {
                    try {
                        val name = document.getString("name") ?: ""
                        val imageUrl = document.getString("image") ?: ""
                        val isBoolean = document.getBoolean("isBoolean") ?: false
                        val isVisible = document.getBoolean("isVisible") ?: false

                        dataset.add(RecyclerData(name, imageUrl, isBoolean, isVisible))
                    } catch (e: Exception) {
                        Log.e("RetrieveData", "Error retrieving document: ${document.id}", e)
                    }
                }

                recyclerAdapter.updateDataset(dataset)
            }
            .addOnFailureListener { exception ->
                Log.e("RetrieveData", "Error fetching documents: ", exception)
                // Handle errors here if needed
            }
    }

    private fun setupListeners(view: View) {
        dropdownIcon.setOnClickListener {
            recyclerAdapter.toggleItems()
        }
        val addDataButton = view.findViewById<Button>(R.id.add_data)
        addDataButton.setOnClickListener {
            findNavController().navigate(R.id.home_Fragment_to_saveFragment)
        }
    }

    private fun setupTabLayout() {
        tabLayout1.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    // Change the background drawable of the entire tab when selected
                    val tabView = tab.view
                    tabView.background = ContextCompat.getDrawable(requireContext(), R.drawable.tab_outline_background)

                    when (it.position) {
                        0 -> adapter1.setData(getAnnouncementItems())
                        1 -> adapter1.setData(getTravelNewsItems())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.let {
                    // Reset the background drawable of the tab when unselected
                    val tabView = tab.view
                    tabView.background = null
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // Add tabs
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
            YourDataModel("For Business class Bookings:    Explore our dedicated business class pages & pick the best flight", R.drawable.vipbusiness),
            YourDataModel("Book Flights with voice chat! Code:  MMTMYRA will be auto-applied for EXTRA discounts!", R.drawable.robotchat),
            YourDataModel("Finding Indian Food just got : Use newly launched filters to find indian food during International trade",R.drawable.foodindian)
        )
    }

    private fun getTravelNewsItems(): List<YourDataModel> {
        return listOf(
            YourDataModel("International Flights Now Call 0124-462     8747 for booking assistance", R.drawable.airplane_svgrepo_com__2_),
            YourDataModel("Wish to travel Abroad? Tap here to check the international destinations open for indians now", R.drawable.airplane_svgrepo_com__2_),
            YourDataModel("By many Indian states, check revised RT-PCR guidelines for travel",R.drawable.flaticon)
            // Add more items as needed
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
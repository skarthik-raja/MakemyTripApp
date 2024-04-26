package com.example.makemytripapp

import CarouselAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Timer
import kotlin.concurrent.timerTask
class MyTripFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var carouselAdapter: CarouselAdapter
    private var currentPage = 0
    private val timer = Timer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_trip, container, false)

        recyclerView = view.findViewById(R.id.mytrips_recyclerview)
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val images = listOf(
            R.drawable.mountain,
            R.drawable.beach,
            R.drawable.romantic,
            R.drawable.weekend,
            R.drawable.international
        )

        val imageDescriptions = listOf(
            "MOUNTAIN",
            "BEACH",
            "ROMANTIC",
            "WEEKEND",
            "INTERNATIONAL"
        )

        carouselAdapter = CarouselAdapter(requireContext(), images, imageDescriptions)
        recyclerView.adapter = carouselAdapter

        startAutoScroll()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

    private fun startAutoScroll() {
        timer.schedule(timerTask {
            activity?.runOnUiThread {
                if (currentPage == carouselAdapter.itemCount - 1) {
                    currentPage = 0
                } else {
                    currentPage++
                }
                recyclerView.smoothScrollToPosition(currentPage)
            }
        }, 1000, 1000)
    }
}

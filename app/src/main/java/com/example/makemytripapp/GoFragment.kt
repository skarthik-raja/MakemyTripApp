package com.example.makemytripapp

import PlaceAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_go, container, false)

        val viewPager: ViewPager2 = view.findViewById(R.id.view_pager)
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)

        // Create adapter for view pager
        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(DiscoverFragment(), "Discover places")
        adapter.addFragment(TravelStoriesFragment(), "Traveller stories")

        // Set adapter to view pager
        viewPager.adapter = adapter

        // Attach TabLayout to ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        return view
    }
}
package com.example.makemytripapp

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class SplashFragment : Fragment() {

    private val SPLASH_DELAY: Long = 2000
    private val cardsList = listOf(
        CardItem(R.drawable.unsplash_images),
        CardItem(R.drawable.mountain),
        CardItem(R.drawable.chennaitravel),
        CardItem(R.drawable.unsplash_images2),
        CardItem(R.drawable.travel3) ,
        CardItem(R.drawable.kanchipuram_image),
        CardItem(R.drawable.marina),
        CardItem(R.drawable.temple_image),
        CardItem(R.drawable.dubai)
        // Add more card items as needed
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_splash, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.cardssplash)
        recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val adapter = CardsSplashAdapter(cardsList)
        recyclerView.adapter = adapter

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
        recyclerView.addItemDecoration(SpacesItemDecoration(spacingInPixels))


        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_languageFragment)
        }, SPLASH_DELAY)

        return rootView
    }

    class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = space
            outRect.right = space
            outRect.bottom = space

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = space
            } else {
                outRect.top = 0
            }
        }
    }
}

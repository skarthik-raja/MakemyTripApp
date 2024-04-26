package com.example.makemytripapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class MytripsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mytrips, container, false)

        val continuebutton = view.findViewById<Button>(R.id.mytrips_button)
        continuebutton.setOnClickListener {
            findNavController().navigate(R.id.action_mytripsfragmet_to_Trips)
        }
        return view;

    }

}
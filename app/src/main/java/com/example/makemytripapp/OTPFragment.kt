package com.example.makemytripapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class OTPFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_o_t_p, container, false)

        val submitbutton = view.findViewById<Button>(R.id.submit_button)

        submitbutton.setOnClickListener {
            findNavController().navigate(R.id.action_otpFragment_to_home_Fragment_)
        }
        return view
    }
}
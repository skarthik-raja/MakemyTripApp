package com.example.makemytripapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import com.hbb20.CountryCodePicker


class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)



        val countryCodePicker = view.findViewById<CountryCodePicker>(R.id.countyCodePicker)

        val button = view.findViewById<Button>(R.id.continue_button)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_otpFragment)
        }

        countryCodePicker.setOnCountryChangeListener {

            val countryName = countryCodePicker.selectedCountryName
            val countryCode = countryCodePicker.selectedCountryCode
            val countryCodeName = countryCodePicker.selectedCountryNameCode
            val countryCodeWithPlus = countryCodePicker.selectedCountryCodeWithPlus

            Toast.makeText(
                requireContext(),
                "$countryName, $countryCode, $countryCodeName, $countryCodeWithPlus",
                Toast.LENGTH_SHORT
            ).show()
        }
        return view
    }

}

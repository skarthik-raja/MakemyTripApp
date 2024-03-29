package com.example.makemytripapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController


class LanguageFragment : Fragment() {

    private lateinit var continueButton: Button
    private lateinit var radioGroupLanguages: RadioGroup
    private lateinit var selectedLanguage: String // Variable to hold the selected language

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_language, container, false)

        continueButton = view.findViewById(R.id.buttonSelectLanguage)
        radioGroupLanguages = view.findViewById(R.id.radioGroupLanguages)

        continueButton.setOnClickListener {
            if (radioGroupLanguages.checkedRadioButtonId != -1) {
                // Get the selected RadioButton
                val selectedRadioButton =
                    view.findViewById<RadioButton>(radioGroupLanguages.checkedRadioButtonId)
                // Get the text of the selected RadioButton
                selectedLanguage = selectedRadioButton.text.toString()

                findNavController().navigate(R.id.action_languageFragment_to_loginFragment)
            } else {
                // Show an error message or handle the case where no language is selected
            }
        }

        return view
    }
}
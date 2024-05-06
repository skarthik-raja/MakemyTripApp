package com.example.makemytripapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.makemytripapp.databinding.FragmentOTPBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OTPFragment : Fragment() {
    private lateinit var binding: FragmentOTPBinding
    private var storeVerificationId: String? = ""
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOTPBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()

        arguments?.let {
            storeVerificationId = it.getString("verification_id")
        }

        val submitButton = binding.submitButton
        submitButton.setOnClickListener {
            val otpCode = binding.editTextOtp.text.toString()
            if (otpCode.isBlank() || otpCode.length < 6) {
                // Invalid OTP input
                Toast.makeText(
                    requireContext(),
                    "Please enter a valid OTP.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                verifyPhoneNumberWithCode(storeVerificationId, otpCode)
            }
        }

        return view
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to the next destination
                    Log.d(TAG, "signInWithCredential:success")
                    findNavController().navigate(R.id.action_otpFragment_to_home_Fragment_)
                } else {
                    // Sign in failed, display a message
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        // Handle the invalid code error

                    }
                    // Update UI or display an error message
                }
            }
    }

    companion object {
        private const val TAG = "OTPFragment"
    }
}

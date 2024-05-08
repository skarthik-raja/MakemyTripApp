package com.example.makemytripapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class SaveFragment : Fragment() {

    private lateinit var saveButton: Button
    private lateinit var firestore: FirebaseFirestore
    private lateinit var imageEditText: ImageButton
    private var selectedImageUri: Uri? = null // Store selected image URI
    private var isImageSelected: Boolean = false // Flag to indicate if image is selected

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_save, container, false)

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        imageEditText = view.findViewById(R.id.imageButtonUpload)

        val editTextName = view.findViewById<EditText>(R.id.editTextname)
        val editTextBoolean = view.findViewById<EditText>(R.id.editTextfalse)
        val editTextIsVisible = view.findViewById<EditText>(R.id.editTextisvisible)
        saveButton = view.findViewById(R.id.upload)

        imageEditText.setOnClickListener {
            openGallery()
        }

        saveButton.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val image = selectedImageUri?.toString().orEmpty() // Use selectedImageUri
            val booleanValue = editTextBoolean.text.toString().trim()
            val isVisible = editTextIsVisible.text.toString().trim()

            // Validate if any field is empty
            if (name.isEmpty() || !isImageSelected || booleanValue.isEmpty() || isVisible.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            // Convert boolean strings to actual boolean values
            val booleanVal = booleanValue.toBooleanOrNull()
            val isVisibleVal = isVisible.toBooleanOrNull()

            // If boolean conversion fails, show an error
            if (booleanVal == null || isVisibleVal == null) {
                Toast.makeText(requireContext(), "Invalid boolean value", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = hashMapOf(
                "name" to name,
                "image" to image,
                "isBoolean" to booleanVal,
                "isVisible" to isVisibleVal
            )

            firestore.collection("HomeData")
                .add(data)
                .addOnSuccessListener { documentReference ->
                    // Handle success
                }
                .addOnFailureListener { e ->
                    // Handle failure
                }

        }

        return view
    }

    fun String?.toBooleanOrNull(): Boolean? {
        return when (this?.toLowerCase()) {
            "true" -> true
            "false" -> false
            else -> null
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            // Set the selected image URI to the ImageButton
            imageEditText.setImageURI(selectedImageUri)
            isImageSelected = true // Set the flag to true
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 1001
    }
}
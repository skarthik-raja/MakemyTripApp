package com.example.makemytripapp

data class MediaModel(
    val resourceId: Int,
    val mediaType: Int, // 0 for image, 1 for video
    val text: String,
    val videoResourceId: Int? = null // Nullable video resource ID
)



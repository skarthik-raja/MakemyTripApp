package com.example.makemytripapp

data class MediaModel(
    val resourceId: Int,
    val mediaType: Int,
    val text: String,
    val videoResourceId: Int? = null ,
    var isPlaying: Boolean = false

)
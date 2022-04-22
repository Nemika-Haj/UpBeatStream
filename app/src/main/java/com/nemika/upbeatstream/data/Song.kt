package com.nemika.upbeatstream.data

data class Song(
    val art: String,
    val artist: String,
    val dislikes: Int,
    val favourites: Int,
    val id: String,
    val likes: Int,
    val played: Int,
    val preview: String,
    val spotify_id: String,
    val title: String
)
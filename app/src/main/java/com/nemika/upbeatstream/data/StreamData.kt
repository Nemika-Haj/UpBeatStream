package com.nemika.upbeatstream.data

data class StreamData(
    val last_updated: Any,
    val listen_url: String,
    val listeners: Int,
    val onair: Onair,
    val song: Song
)
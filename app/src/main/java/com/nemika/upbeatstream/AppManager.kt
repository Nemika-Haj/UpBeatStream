package com.nemika.upbeatstream

import android.app.Application
import android.media.MediaPlayer

val mediaPlayer = MediaPlayer()

class AppManager: Application() {

    fun getMediaPlayer(): MediaPlayer {
        return mediaPlayer
    }
}
package com.nemika.upbeatstream

import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val AUDIO_URL = "https://live.upbeat.pw/"

class Loading : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        Thread(Runnable() {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            mediaPlayer.setDataSource(AUDIO_URL)
            mediaPlayer.prepare()

            startActivity(Intent(this@Loading, MainActivity::class.java))
            finish()
        }).start()
    }
}
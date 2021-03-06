package com.nemika.upbeatstream

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nemika.upbeatstream.data.StreamData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timer().scheduleAtFixedRate(timerTask {
            Log.d("LOOPER", "Loop!")

            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://upbeatradio.net/api/v1/")
                .build()
                .create(ApiInterface::class.java)

            val streamData = retrofitBuilder.getStream()
            streamData.enqueue(object : Callback<StreamData?> {
                @SuppressLint("SetTextI18n")
                override fun onResponse(call: Call<StreamData?>, response: Response<StreamData?>) {
                    try {
                        val body = response.body()!!
                        val songView: TextView = findViewById(R.id.songName)
                        val djView: TextView = findViewById(R.id.djName)
                        val djAvatar: ImageView = findViewById(R.id.djAvatar)

                        val songName = "${body.song.title} by ${body.song.artist}"

                        if (songName != songView.text) {
                            songView.text = songName
                            djView.text = "On Air: ${body.onair.name}"
                            Glide
                                .with(this@MainActivity)
                                .load(body.onair.avatar)
                                .placeholder(R.drawable.ubs)
                                .into(djAvatar)

                        }

                    } catch (e: Exception) {
                        Toast.makeText(this@MainActivity, "Can't stream music, try again later.", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<StreamData?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Can't stream music, try again later.", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            })
        }, 0, 10000)

        findViewById<TextView>(R.id.songName).isSelected = true

        val playButton: ImageView = findViewById(R.id.playButton)

        playButton.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                playAudio()

                playButton.setImageResource(R.drawable.ubs_stop_button)
            } else {
                mediaPlayer.pause()

                playButton.setImageResource(R.drawable.ubs_play_button)
            }
        }
    }

    private fun playAudio() {

        try {
            mediaPlayer.start()
        } catch (e: Exception) {
            Toast.makeText(this, "Can't stream music, try again later.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }


}
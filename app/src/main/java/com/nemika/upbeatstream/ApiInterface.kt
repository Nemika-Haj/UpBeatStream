package com.nemika.upbeatstream

import com.nemika.upbeatstream.data.StreamData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("stats")
    fun getStream(): Call<StreamData>
}
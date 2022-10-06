package com.example.challengentconsult.api

import com.example.challengentconsult.model.CheckInModel
import com.example.challengentconsult.model.EventModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("events")
    fun getEvents(): Call<MutableList<EventModel>>

    @POST("checkin")
    fun postCheckIn(@Body postCheckIn: CheckInModel): Call<EventModel>
}
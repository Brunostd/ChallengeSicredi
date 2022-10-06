package com.example.challengentconsult.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challengentconsult.api.ApiService
import com.example.challengentconsult.model.EventModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class EventRepository {

    var listEvent = MutableLiveData<MutableList<EventModel>>()

    fun getEvents(): LiveData<MutableList<EventModel>>{

        var retrofit = Retrofit.Builder()
            .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(ApiService::class.java)
        var call: Call<MutableList<EventModel>> = service.getEvents()

        call.enqueue(object : Callback<MutableList<EventModel>>{
            override fun onResponse(
                call: Call<MutableList<EventModel>>,
                response: Response<MutableList<EventModel>>
            ) {
                listEvent.value = response.body()
            }

            override fun onFailure(call: Call<MutableList<EventModel>>, t: Throwable) {
            }

        })

        return listEvent
    }

}
package com.example.challengentconsult.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challengentconsult.api.ApiService
import com.example.challengentconsult.model.CheckInModel
import com.example.challengentconsult.model.CheckInResponse
import com.example.challengentconsult.model.EventModel
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class CheckInRepository {

    private var auxCheckIn = MutableLiveData<CheckInResponse>()

    fun postCheckIn(checkInModel: CheckInModel): LiveData<CheckInResponse>{
        var retrofit = Retrofit.Builder()
            .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var service = retrofit.create(ApiService::class.java)
        var call: Call<CheckInResponse> = service.postCheckIn(checkInModel)

        call.enqueue(object : Callback<CheckInResponse>{
            override fun onResponse(call: Call<CheckInResponse>, response: Response<CheckInResponse>) {
                auxCheckIn.value = response.body()
            }
            override fun onFailure(call: Call<CheckInResponse>, t: Throwable) {

            }
        })
        return auxCheckIn
    }
}
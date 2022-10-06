package com.example.challengentconsult.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengentconsult.model.EventModel
import com.example.challengentconsult.repository.EventRepository

class HomeViewModel: ViewModel() {

    private var listEvent: MutableLiveData<MutableList<EventModel>>? = null

    fun getEvents(): LiveData<MutableList<EventModel>>{
        return EventRepository().getEvents()
    }

    fun getList(list: MutableList<EventModel>): MutableLiveData<MutableList<EventModel>>{
        list.forEach { event ->
            var auxInitImage = event.image.substring(0,5)
            if (auxInitImage != "https"){
                var auxImage = event.image.substring(5)
                event.image = "https:" + auxImage
            }
        }
        var auxList = MutableLiveData<MutableList<EventModel>>().apply {
            value = list
        }
        return auxList
    }
}
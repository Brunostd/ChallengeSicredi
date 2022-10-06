package com.example.challengentconsult.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challengentconsult.model.CheckInModel
import com.example.challengentconsult.model.EventModel
import com.example.challengentconsult.repository.CheckInRepository

class CheckInViewModel: ViewModel() {

    private var checkInRepository: CheckInRepository = CheckInRepository()
    private var response = MutableLiveData<EventModel>()

    fun postCheckIn(checkInModel: CheckInModel): LiveData<EventModel>{
        return checkInRepository.postCheckIn(checkInModel)
    }
}
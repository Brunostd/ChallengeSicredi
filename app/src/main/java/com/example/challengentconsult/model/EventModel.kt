package com.example.challengentconsult.model

import java.io.Serializable

class EventModel(
    var id: Int,
    var title: String,
    var image: String,
    var price: Double,
    var date: Double,
    var description: String
): Serializable {
}
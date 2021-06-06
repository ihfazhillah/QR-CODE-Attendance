package com.ihfazh.absensiqrcode.domains.events.models


data class Event (
    val eventId: String,
    val title: String,
    val description: String?,
    val datetime: String
)
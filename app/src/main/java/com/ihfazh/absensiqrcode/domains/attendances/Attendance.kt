package com.ihfazh.absensiqrcode.domains.attendances

import Event.Student
import com.ihfazh.absensiqrcode.domains.events.models.Event

data class Attendance(
    val event: Event,
    val student: Student,
    val date: String,
)

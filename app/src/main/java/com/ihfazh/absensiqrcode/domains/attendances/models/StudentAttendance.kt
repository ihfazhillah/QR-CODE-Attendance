package com.ihfazh.absensiqrcode.domains.attendances.models

import Event.Student

data class StudentAttendance(
        val id: Long,
        val eventId: String?,
        val student: Student,
        val date: String
)

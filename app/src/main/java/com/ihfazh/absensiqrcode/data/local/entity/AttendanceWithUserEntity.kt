package com.ihfazh.absensiqrcode.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class AttendanceWithUserEntity(
        @Relation(
                parentColumn = "studentId",
                entityColumn = "studentId"
        ) val student: StudentEntity,
        @Embedded val attendance: AttendanceEntity
)

package com.ihfazh.absensiqrcode.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        tableName = "attendance",
        foreignKeys = [
            ForeignKey(entity = StudentEntity::class, parentColumns = ["studentId"] , childColumns= ["studentId"], onDelete = ForeignKey.SET_NULL),
            ForeignKey(entity = EventEntity::class, parentColumns = ["eventId"] , childColumns= ["eventId"], onDelete = ForeignKey.SET_NULL)
        ]
)
data class AttendanceEntity(
    @PrimaryKey(autoGenerate = true) val id : Long,
    @ColumnInfo val studentId: String?,
    @ColumnInfo val eventId: String?,
    @ColumnInfo val date: String
)
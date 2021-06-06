package com.ihfazh.absensiqrcode.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
    @PrimaryKey val eventId: String,
    @ColumnInfo val title: String,
    @ColumnInfo val description: String?,
    @ColumnInfo val datetime: String
)
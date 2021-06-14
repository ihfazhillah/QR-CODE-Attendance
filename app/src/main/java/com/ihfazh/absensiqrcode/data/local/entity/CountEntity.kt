package com.ihfazh.absensiqrcode.data.local.entity

import androidx.room.Entity

@Entity
data class CountEntity(
        val eventCount: Int?,
        val studentCount: Int?,
)

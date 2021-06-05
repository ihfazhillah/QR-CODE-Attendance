package com.ihfazh.absensiqrcode.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class StudentEntity(
    @PrimaryKey val studentId: String,
    @ColumnInfo val firstName: String,
    @ColumnInfo val lastName: String
)

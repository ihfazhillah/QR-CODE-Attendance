package com.ihfazh.absensiqrcode.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ihfazh.absensiqrcode.data.local.entity.EventEntity
import com.ihfazh.absensiqrcode.data.local.entity.StudentEntity

@Database(
    entities = [StudentEntity::class, EventEntity::class],
    version = 2,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun eventDao(): EventDao
}
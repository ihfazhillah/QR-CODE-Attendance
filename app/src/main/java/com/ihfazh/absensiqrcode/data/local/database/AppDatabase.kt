package com.ihfazh.absensiqrcode.data.local.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.ihfazh.absensiqrcode.data.local.entity.AttendanceEntity
import com.ihfazh.absensiqrcode.data.local.entity.EventEntity
import com.ihfazh.absensiqrcode.data.local.entity.StudentEntity

@Database(
    entities = [StudentEntity::class, EventEntity::class, AttendanceEntity::class],
    version = 3,
    exportSchema = true,
        autoMigrations = [AutoMigration(from=2, to=3)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun eventDao(): EventDao
    abstract fun attendanceDao(): AttendanceDao
}
package com.ihfazh.absensiqrcode.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ihfazh.absensiqrcode.data.local.entity.AttendanceEntity
import io.reactivex.Completable

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(attendanceEntity: AttendanceEntity): Completable

}

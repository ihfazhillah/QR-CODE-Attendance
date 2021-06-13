package com.ihfazh.absensiqrcode.data.local.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ihfazh.absensiqrcode.data.local.entity.AttendanceEntity
import com.ihfazh.absensiqrcode.data.local.entity.AttendanceWithUserEntity
import io.reactivex.Completable

@Dao
interface AttendanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(attendanceEntity: AttendanceEntity): Completable

    @Query("SELECT * from attendance where eventId = :eventId")
    fun getHistoriesByEvent(eventId: String): DataSource.Factory<Int, AttendanceWithUserEntity>
}

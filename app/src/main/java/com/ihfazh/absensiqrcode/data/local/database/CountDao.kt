package com.ihfazh.absensiqrcode.data.local.database

import androidx.room.Dao
import androidx.room.Query
import com.ihfazh.absensiqrcode.data.local.entity.CountEntity
import io.reactivex.Flowable

@Dao
interface CountDao {
    @Query("""
        select (select count(*) from event) as eventCount, (select count(*) from student) as studentCount
    """)
    fun detail(): Flowable<CountEntity>
}
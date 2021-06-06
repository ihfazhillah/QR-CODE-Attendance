package com.ihfazh.absensiqrcode.data.local.database

import androidx.paging.DataSource
import androidx.room.*
import com.ihfazh.absensiqrcode.data.local.entity.EventEntity
import io.reactivex.Completable

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(eventEntity: EventEntity): Completable

    @Delete
    fun delete(eventEntity: EventEntity): Completable

    @Query("SELECT * from event")
    fun list(): DataSource.Factory<Int, EventEntity>
}
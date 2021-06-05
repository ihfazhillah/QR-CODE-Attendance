package com.ihfazh.absensiqrcode.data.local.database

import androidx.paging.DataSource
import androidx.room.*
import com.ihfazh.absensiqrcode.data.local.entity.StudentEntity
import io.reactivex.Completable

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(studentEntity: StudentEntity): Completable

    @Delete
    fun delete(studentEntity: StudentEntity): Completable

    @Query("SELECT * from student")
    fun list(): DataSource.Factory<Int, StudentEntity>
}
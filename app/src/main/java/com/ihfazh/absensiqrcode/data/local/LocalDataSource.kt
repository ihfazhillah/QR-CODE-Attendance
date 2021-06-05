package com.ihfazh.absensiqrcode.data.local

import com.ihfazh.absensiqrcode.data.local.database.AppDatabase
import com.ihfazh.absensiqrcode.data.local.entity.StudentEntity
import io.reactivex.Completable
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val database: AppDatabase) {
    fun addStudent(studentEntity: StudentEntity) = database.studentDao().add(studentEntity)
    fun deleteStudent(studentEntity: StudentEntity) = database.studentDao().delete(studentEntity)
    fun listStudent() = database.studentDao().list()
}
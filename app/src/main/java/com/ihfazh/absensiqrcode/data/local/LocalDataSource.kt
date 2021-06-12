package com.ihfazh.absensiqrcode.data.local

import com.ihfazh.absensiqrcode.data.local.database.AppDatabase
import com.ihfazh.absensiqrcode.data.local.entity.AttendanceEntity
import com.ihfazh.absensiqrcode.data.local.entity.EventEntity
import com.ihfazh.absensiqrcode.data.local.entity.StudentEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val database: AppDatabase) {
    fun addStudent(studentEntity: StudentEntity) = database.studentDao().add(studentEntity)
    fun deleteStudent(studentEntity: StudentEntity) = database.studentDao().delete(studentEntity)
    fun listStudent() = database.studentDao().list()
    fun listAllStudents() = database.studentDao().listAll()
    fun detailStudent(studentId: String) = database.studentDao().detail(studentId)

    // event
    fun addEvent(eventEntity: EventEntity) = database.eventDao().add(eventEntity)
    fun deleteEvent(eventEntity: EventEntity) = database.eventDao().delete(eventEntity)
    fun listEvent() = database.eventDao().list()
    fun eventDetail(eventId: String) = database.eventDao().detail(eventId)
    fun addStudentAttendance(attendanceEntity: AttendanceEntity) = database.attendanceDao().insert(attendanceEntity)
}
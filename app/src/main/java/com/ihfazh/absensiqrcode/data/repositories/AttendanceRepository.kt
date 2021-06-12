package com.ihfazh.absensiqrcode.data.repositories

import com.ihfazh.absensiqrcode.data.local.LocalDataSource
import com.ihfazh.absensiqrcode.data.local.entity.AttendanceEntity
import com.ihfazh.absensiqrcode.domains.attendances.Attendance
import com.ihfazh.absensiqrcode.domains.attendances.IAttendanceRepository
import io.reactivex.Completable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AttendanceRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : IAttendanceRepository {
    override fun addStudentAttendance(attendance: Attendance): Completable {
        return localDataSource.addStudentAttendance(
            AttendanceEntity(id = 0, studentId = attendance.student.studentId, eventId = attendance.event.eventId, date = attendance.date)
        )
    }
}
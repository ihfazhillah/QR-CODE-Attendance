package com.ihfazh.absensiqrcode.data.repositories

import Event.Student
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.ihfazh.absensiqrcode.data.local.LocalDataSource
import com.ihfazh.absensiqrcode.data.local.entity.AttendanceEntity
import com.ihfazh.absensiqrcode.domains.attendances.Attendance
import com.ihfazh.absensiqrcode.domains.attendances.IAttendanceRepository
import com.ihfazh.absensiqrcode.domains.attendances.models.StudentAttendance
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
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

    override fun getHistoriesByEvent(eventId: String): Flowable<PagingData<StudentAttendance>> {
        return Pager(
                PagingConfig(20),
                null,
                localDataSource.getHistoriesByEvent(eventId).map {
                    StudentAttendance(it.attendance.id, it.attendance.eventId, Student(it.student.studentId, it.student.firstName, it.student.lastName), it.attendance.date)
                }.asPagingSourceFactory(Dispatchers.IO)
        ).flowable
    }
}
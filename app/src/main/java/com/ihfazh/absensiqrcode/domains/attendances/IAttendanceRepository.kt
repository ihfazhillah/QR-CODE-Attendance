package com.ihfazh.absensiqrcode.domains.attendances

import androidx.paging.PagingData
import com.ihfazh.absensiqrcode.domains.attendances.models.StudentAttendance
import io.reactivex.Completable
import io.reactivex.Flowable

interface IAttendanceRepository {
    fun addStudentAttendance(attendance: Attendance): Completable
    fun getHistoriesByEvent(eventId: String): Flowable<PagingData<StudentAttendance>>
}
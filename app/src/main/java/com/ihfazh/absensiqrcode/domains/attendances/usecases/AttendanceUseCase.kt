package com.ihfazh.absensiqrcode.domains.attendances.usecases

import androidx.paging.PagingData
import com.ihfazh.absensiqrcode.domains.attendances.Attendance
import com.ihfazh.absensiqrcode.domains.attendances.models.StudentAttendance
import io.reactivex.Completable
import io.reactivex.Flowable

interface AttendanceUseCase {
    fun addStudentAttendance(attendance: Attendance): Completable
    fun getHistoriesByEvent(eventId: String): Flowable<PagingData<StudentAttendance>>
}
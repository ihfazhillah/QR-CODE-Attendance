package com.ihfazh.absensiqrcode.domains.attendances.usecases

import com.ihfazh.absensiqrcode.domains.attendances.Attendance
import io.reactivex.Completable

interface AttendanceUseCase {
    fun addStudentAttendance(attendance: Attendance): Completable
}
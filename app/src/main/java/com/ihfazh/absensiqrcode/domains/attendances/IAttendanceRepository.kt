package com.ihfazh.absensiqrcode.domains.attendances

import io.reactivex.Completable

interface IAttendanceRepository {
    fun addStudentAttendance(attendance: Attendance): Completable
}
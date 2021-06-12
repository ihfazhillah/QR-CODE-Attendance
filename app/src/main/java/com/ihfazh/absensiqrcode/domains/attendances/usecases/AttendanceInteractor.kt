package com.ihfazh.absensiqrcode.domains.attendances.usecases

import com.ihfazh.absensiqrcode.domains.attendances.Attendance
import com.ihfazh.absensiqrcode.domains.attendances.IAttendanceRepository
import io.reactivex.Completable

class AttendanceInteractor(private val repository: IAttendanceRepository) : AttendanceUseCase {
    override fun addStudentAttendance(attendance: Attendance): Completable {
        return repository.addStudentAttendance(attendance)
    }

}
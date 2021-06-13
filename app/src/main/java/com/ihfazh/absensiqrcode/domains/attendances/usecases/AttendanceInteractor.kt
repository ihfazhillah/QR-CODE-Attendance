package com.ihfazh.absensiqrcode.domains.attendances.usecases

import androidx.paging.PagingData
import com.ihfazh.absensiqrcode.domains.attendances.Attendance
import com.ihfazh.absensiqrcode.domains.attendances.IAttendanceRepository
import com.ihfazh.absensiqrcode.domains.attendances.models.StudentAttendance
import io.reactivex.Completable
import io.reactivex.Flowable

class AttendanceInteractor(private val repository: IAttendanceRepository) : AttendanceUseCase {
    override fun addStudentAttendance(attendance: Attendance): Completable {
        return repository.addStudentAttendance(attendance)
    }

    override fun getHistoriesByEvent(eventId: String): Flowable<PagingData<StudentAttendance>> {
        return repository.getHistoriesByEvent(eventId)
    }
}
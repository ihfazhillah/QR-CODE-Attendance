package com.ihfazh.absensiqrcode.ui.detailevent

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.attendances.Attendance
import com.ihfazh.absensiqrcode.domains.attendances.usecases.AttendanceUseCase
import com.ihfazh.absensiqrcode.domains.events.usecases.EventUseCase
import com.ihfazh.absensiqrcode.domains.students.usecases.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailEventViewModel @Inject constructor(
        private val eventUseCase: EventUseCase,
        private val studentUseCase: StudentUseCase,
        private val attendanceUseCase: AttendanceUseCase
    ): ViewModel() {
    private val eventId = MutableLiveData("")
    fun setEventId(eventId: String){
        this.eventId.postValue(eventId)
    }

    val eventDetail = Transformations.switchMap(eventId){
        LiveDataReactiveStreams.fromPublisher(eventUseCase.eventDetail(it))
    }

    private val studentId = MutableLiveData("")
    fun setStudentId(studentId: String){
        this.studentId.postValue(studentId)
    }
    val studentDetail = Transformations.switchMap(studentId){
        LiveDataReactiveStreams.fromPublisher(studentUseCase.detail(it))
    }

    fun addStudentAttendance(): Completable? {
        val date = Date().toString()
        val event = eventDetail.value
        val student = studentDetail.value

        if (event != null && student != null){
            return attendanceUseCase.addStudentAttendance(Attendance(event, student, date))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
        return null
    }

}
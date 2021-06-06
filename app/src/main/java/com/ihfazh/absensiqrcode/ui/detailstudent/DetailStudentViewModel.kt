package com.ihfazh.absensiqrcode.ui.detailstudent

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.students.usecases.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailStudentViewModel @Inject constructor(private val useCase: StudentUseCase): ViewModel() {
    fun getStudent(studentId: String) = LiveDataReactiveStreams.fromPublisher(
        useCase.detail(studentId)
    )
}
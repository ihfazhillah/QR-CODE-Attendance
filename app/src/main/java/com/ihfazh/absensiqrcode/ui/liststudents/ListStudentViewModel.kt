package com.ihfazh.absensiqrcode.ui.liststudents

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.students.usecases.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListStudentViewModel @Inject constructor(private val useCase: StudentUseCase): ViewModel() {
    fun getStudents() = LiveDataReactiveStreams.fromPublisher(
        useCase.list()
    )
}
package com.ihfazh.absensiqrcode.ui.addstudent

import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.students.models.Student
import com.ihfazh.absensiqrcode.domains.students.usecases.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AddStudentViewModel @Inject constructor(private val useCase: StudentUseCase): ViewModel() {
    fun addStudent(student: Student) = useCase.add(student)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
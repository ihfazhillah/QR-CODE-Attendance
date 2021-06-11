package com.ihfazh.absensiqrcode.ui.liststudents

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import com.ihfazh.absensiqrcode.domains.students.usecases.StudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ListStudentViewModel @Inject constructor(private val useCase: StudentUseCase) : ViewModel() {
    fun getStudents() = LiveDataReactiveStreams.fromPublisher(
        useCase.list()
    )

    fun exportStudentsData(path: String, onSuccess: () -> Unit): Disposable {
        return useCase.listAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { students ->
                    val rows = students.map {
                        listOf(it.studentId, "${it.firstName} ${it.lastName}", "attendanceqrcode.${it.studentId}")
                    }
                    csvWriter().writeAll(rows, path)
                    onSuccess()
                },
                {},
            )

    }
}
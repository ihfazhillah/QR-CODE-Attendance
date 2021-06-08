package com.ihfazh.absensiqrcode.domains.students.usecases

import Event.Student
import androidx.paging.PagingData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

interface StudentUseCase {
    fun add(student: Student): Completable
    fun delete(student: Student): Completable
    fun list(): Flowable<PagingData<Student>>
    fun listAll(): Maybe<List<Student>>
    fun detail(studentId: String): Flowable<Student>
}
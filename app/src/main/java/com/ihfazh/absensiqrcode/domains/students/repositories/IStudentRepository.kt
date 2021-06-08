package com.ihfazh.absensiqrcode.domains.students.repositories

import Event.Student
import androidx.paging.PagingData
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

interface IStudentRepository {
    fun add(student: Student): Completable
    fun delete(student: Student): Completable
    fun list(): Flowable<PagingData<Student>>
    fun detail(studentId: String): Flowable<Student>
    fun listAll(): Maybe<List<Student>>

}
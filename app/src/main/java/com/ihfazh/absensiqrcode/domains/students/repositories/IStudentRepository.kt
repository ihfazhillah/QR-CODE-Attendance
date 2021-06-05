package com.ihfazh.absensiqrcode.domains.students.repositories

import androidx.paging.PagingData
import com.ihfazh.absensiqrcode.domains.students.models.Student
import io.reactivex.Completable
import io.reactivex.Flowable

interface IStudentRepository {
    fun add(student: Student): Completable
    fun delete(student: Student): Completable
    fun list(): Flowable<PagingData<Student>>
}
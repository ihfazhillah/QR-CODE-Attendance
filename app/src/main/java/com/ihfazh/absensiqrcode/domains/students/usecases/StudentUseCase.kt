package com.ihfazh.absensiqrcode.domains.students.usecases

import androidx.paging.PagedList
import com.ihfazh.absensiqrcode.domains.students.models.Student
import io.reactivex.Completable
import io.reactivex.Flowable

interface StudentUseCase {
    fun add(student: Student): Completable
    fun delete(student: Student): Completable
    fun list(): Flowable<PagedList<Student>>
}
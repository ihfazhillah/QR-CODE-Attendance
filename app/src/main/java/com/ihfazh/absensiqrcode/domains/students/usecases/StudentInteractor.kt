package com.ihfazh.absensiqrcode.domains.students.usecases

import androidx.paging.PagedList
import com.ihfazh.absensiqrcode.domains.students.models.Student
import com.ihfazh.absensiqrcode.domains.students.repositories.IStudentRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class StudentInteractor(val repository: IStudentRepository): StudentUseCase {
    override fun add(student: Student): Completable {
        return repository.add(student)
    }

    override fun delete(student: Student): Completable {
        return repository.delete(student)
    }

    override fun list(): Flowable<PagedList<Student>> {
        return repository.list()
    }

}
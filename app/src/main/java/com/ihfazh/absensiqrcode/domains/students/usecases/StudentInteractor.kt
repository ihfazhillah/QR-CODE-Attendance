package com.ihfazh.absensiqrcode.domains.students.usecases

import Event.Student
import com.ihfazh.absensiqrcode.domains.students.repositories.IStudentRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

class StudentInteractor(private val repository: IStudentRepository) : StudentUseCase {
    override fun add(student: Student): Completable {
        return repository.add(student)
    }

    override fun delete(student: Student): Completable {
        return repository.delete(student)
    }

    override fun list() = repository.list()
    override fun listAll(): Maybe<List<Student>> {
        return repository.listAll()
    }

    override fun detail(studentId: String): Flowable<Student> {
        return repository.detail(studentId)
    }

}
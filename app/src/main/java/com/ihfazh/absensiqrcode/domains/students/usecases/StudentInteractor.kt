package com.ihfazh.absensiqrcode.domains.students.usecases

import Event.Student
import com.ihfazh.absensiqrcode.domains.events.repositories.IEventRepository
import com.ihfazh.absensiqrcode.domains.students.repositories.IStudentRepository
import io.reactivex.Completable

class StudentInteractor(private val repository: IStudentRepository): StudentUseCase {
    override fun add(student: Student): Completable {
        return repository.add(student)
    }

    override fun delete(student: Student): Completable {
        return repository.delete(student)
    }

    override fun list() = repository.list()

}
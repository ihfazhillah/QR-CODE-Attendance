package com.ihfazh.absensiqrcode.data.repositories

import androidx.paging.*
import androidx.paging.rxjava2.flowable
import com.ihfazh.absensiqrcode.data.local.LocalDataSource
import com.ihfazh.absensiqrcode.data.local.entity.StudentEntity
import com.ihfazh.absensiqrcode.domains.students.models.Student
import com.ihfazh.absensiqrcode.domains.students.repositories.IStudentRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class StudentRepository @Inject constructor(
    private val localDataSource: LocalDataSource
): IStudentRepository {
    override fun add(student: Student): Completable {
        return localDataSource.addStudent(
            StudentEntity(student.studentId, student.firstName, student.lastName)
        )
    }

    override fun delete(student: Student): Completable {
        return localDataSource.deleteStudent(
            StudentEntity(student.studentId, student.firstName, student.lastName)
        )
    }

    override fun list(): Flowable<PagingData<Student>> {
        return Pager(
            PagingConfig(20),
            null,
            localDataSource.listStudent().map {
                Student(it.studentId, it.firstName, it.lastName)
            }.asPagingSourceFactory(Dispatchers.IO)
        ).flowable
    }
}
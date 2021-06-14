package com.ihfazh.absensiqrcode.di.modules

import com.ihfazh.absensiqrcode.data.repositories.AttendanceRepository
import com.ihfazh.absensiqrcode.data.repositories.CountRepository
import com.ihfazh.absensiqrcode.data.repositories.EventRepository
import com.ihfazh.absensiqrcode.data.repositories.StudentRepository
import com.ihfazh.absensiqrcode.domains.attendances.usecases.AttendanceInteractor
import com.ihfazh.absensiqrcode.domains.attendances.usecases.AttendanceUseCase
import com.ihfazh.absensiqrcode.domains.counts.usecases.CountInteractor
import com.ihfazh.absensiqrcode.domains.counts.usecases.CountUseCase
import com.ihfazh.absensiqrcode.domains.events.usecases.EventInteractor
import com.ihfazh.absensiqrcode.domains.events.usecases.EventUseCase
import com.ihfazh.absensiqrcode.domains.students.usecases.StudentInteractor
import com.ihfazh.absensiqrcode.domains.students.usecases.StudentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(SingletonComponent::class)
class InteractorModule {
    @ExperimentalCoroutinesApi
    @Provides
    fun provideStudentUseCase(studentRepository: StudentRepository): StudentUseCase =
        StudentInteractor(studentRepository)

    @ExperimentalCoroutinesApi
    @Provides
    fun provideEventUseCase(eventRepository: EventRepository): EventUseCase =
        EventInteractor(eventRepository)

    @ExperimentalCoroutinesApi
    @Provides
    fun provideAttendanceUseCase(repository: AttendanceRepository): AttendanceUseCase = AttendanceInteractor(repository)

    @ExperimentalCoroutinesApi
    @Provides
    fun provideCountUseCase(repository: CountRepository): CountUseCase = CountInteractor(repository)
}
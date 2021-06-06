package com.ihfazh.absensiqrcode.di.modules

import com.ihfazh.absensiqrcode.data.repositories.EventRepository
import com.ihfazh.absensiqrcode.data.repositories.StudentRepository
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
}
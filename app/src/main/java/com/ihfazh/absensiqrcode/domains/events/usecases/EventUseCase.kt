package com.ihfazh.absensiqrcode.domains.events.usecases

import androidx.paging.PagingData
import com.ihfazh.absensiqrcode.domains.events.models.Event
import io.reactivex.Completable
import io.reactivex.Flowable

interface EventUseCase {
    fun add(event: Event): Completable
    fun delete(event: Event): Completable
    fun list(): Flowable<PagingData<Event>>
    fun eventDetail(eventId: String): Flowable<Event>
}
package com.ihfazh.absensiqrcode.domains.events.usecases

import androidx.paging.PagingData
import com.ihfazh.absensiqrcode.domains.events.models.Event
import com.ihfazh.absensiqrcode.domains.events.repositories.IEventRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class EventInteractor(private val repository: IEventRepository) : EventUseCase {
    override fun add(event: Event): Completable {
        return repository.add(event)
    }

    override fun delete(event: Event): Completable {
        return repository.delete(event)
    }

    override fun list(): Flowable<PagingData<Event>> {
        return repository.list()
    }
}
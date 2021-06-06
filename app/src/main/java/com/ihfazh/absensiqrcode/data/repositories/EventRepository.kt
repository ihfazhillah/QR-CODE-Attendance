package com.ihfazh.absensiqrcode.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.ihfazh.absensiqrcode.data.local.LocalDataSource
import com.ihfazh.absensiqrcode.data.local.entity.EventEntity
import com.ihfazh.absensiqrcode.domains.events.models.Event
import com.ihfazh.absensiqrcode.domains.events.repositories.IEventRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class EventRepository @Inject constructor(
    private val localDataSource: LocalDataSource
) : IEventRepository {
    override fun add(event: Event): Completable {
        return localDataSource.addEvent(
            EventEntity(event.eventId, event.title, event.description, event.datetime)
        )
    }

    override fun delete(event: Event): Completable {
        return localDataSource.deleteEvent(
            EventEntity(event.eventId, event.title, event.description, event.datetime)
        )
    }

    override fun list(): Flowable<PagingData<Event>> {
        return Pager(
            PagingConfig(20),
            null,
            localDataSource.listEvent().map {
                Event(it.eventId, it.title, it.description, it.datetime)
            }.asPagingSourceFactory(Dispatchers.IO)
        ).flowable
    }
}
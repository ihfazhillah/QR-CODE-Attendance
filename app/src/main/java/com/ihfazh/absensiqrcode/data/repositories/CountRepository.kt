package com.ihfazh.absensiqrcode.data.repositories

import com.ihfazh.absensiqrcode.data.local.LocalDataSource
import com.ihfazh.absensiqrcode.domains.counts.models.Count
import com.ihfazh.absensiqrcode.domains.counts.repositories.ICountRepository
import io.reactivex.Flowable
import javax.inject.Inject

class CountRepository @Inject constructor(
        private val localDataSource: LocalDataSource
): ICountRepository {

    override fun detail(): Flowable<Count> {
        return localDataSource.getCount().map {
            Count(it.eventCount, it.studentCount)
        }
    }
}
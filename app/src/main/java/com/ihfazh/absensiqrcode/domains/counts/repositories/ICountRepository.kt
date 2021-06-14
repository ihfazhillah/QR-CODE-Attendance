package com.ihfazh.absensiqrcode.domains.counts.repositories

import com.ihfazh.absensiqrcode.domains.counts.models.Count
import io.reactivex.Flowable

interface ICountRepository {
    fun detail(): Flowable<Count>
}
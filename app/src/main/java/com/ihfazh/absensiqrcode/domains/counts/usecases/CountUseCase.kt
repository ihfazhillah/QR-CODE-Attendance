package com.ihfazh.absensiqrcode.domains.counts.usecases

import com.ihfazh.absensiqrcode.domains.counts.models.Count
import io.reactivex.Flowable


interface CountUseCase {
    fun detail(): Flowable<Count>
}
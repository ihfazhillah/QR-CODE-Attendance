package com.ihfazh.absensiqrcode.domains.counts.usecases

import com.ihfazh.absensiqrcode.domains.counts.models.Count
import com.ihfazh.absensiqrcode.domains.counts.repositories.ICountRepository
import io.reactivex.Flowable

class CountInteractor(private val repository: ICountRepository) : CountUseCase {
    override fun detail(): Flowable<Count> {
        return repository.detail()
    }
}
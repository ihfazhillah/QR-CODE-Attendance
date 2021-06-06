package com.ihfazh.absensiqrcode.ui.addevent

import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.events.models.Event
import com.ihfazh.absensiqrcode.domains.events.usecases.EventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AddEventViewModel @Inject constructor(private val useCase: EventUseCase) : ViewModel() {
    fun addEvent(event: Event) = useCase.add(event)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
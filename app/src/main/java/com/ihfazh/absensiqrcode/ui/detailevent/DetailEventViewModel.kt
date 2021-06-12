package com.ihfazh.absensiqrcode.ui.detailevent

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.events.usecases.EventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailEventViewModel @Inject constructor(private val eventUseCase: EventUseCase): ViewModel() {
    private val eventId = MutableLiveData("")
    fun setEventId(eventId: String){
        this.eventId.postValue(eventId)
    }

    val eventDetail = Transformations.switchMap(eventId){
        LiveDataReactiveStreams.fromPublisher(eventUseCase.eventDetail(it))
    }

}
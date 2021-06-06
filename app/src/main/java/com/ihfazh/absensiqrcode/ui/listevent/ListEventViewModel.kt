package com.ihfazh.absensiqrcode.ui.listevent

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.events.usecases.EventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListEventViewModel @Inject constructor(private val useCase: EventUseCase) : ViewModel() {
    fun list() = LiveDataReactiveStreams.fromPublisher(
        useCase.list()
    )
}
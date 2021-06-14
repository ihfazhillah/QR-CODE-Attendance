package com.ihfazh.absensiqrcode.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ihfazh.absensiqrcode.domains.counts.usecases.CountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val countUseCase: CountUseCase
): ViewModel() {
    fun getCount() = LiveDataReactiveStreams.fromPublisher(countUseCase.detail())
}
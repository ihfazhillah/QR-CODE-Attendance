package com.ihfazh.absensiqrcode.ui.qrcodecamera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRCodeViewModel : ViewModel() {
    val result = MutableLiveData<String?>()
}
package com.ihfazh.absensiqrcode.ui.qrcodecamera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QRCodeViewModel  constructor(
//    studentUseCase: StudentUseCase,
) : ViewModel() {
    val result = MutableLiveData<String?>()

    // pilih event
    // scan ->
    // kalau dapat datanya di db, tampilkan di screen detail nya
    // menu selesai clicked -> tambahkan ke event
}
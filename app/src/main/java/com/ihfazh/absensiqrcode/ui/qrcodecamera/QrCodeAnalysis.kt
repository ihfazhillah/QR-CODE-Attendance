package com.ihfazh.absensiqrcode.ui.qrcodecamera

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

class QrCodeAnalysis(private val listener: QRCodeListener): ImageAnalysis.Analyzer {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy:  ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null){
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            val scanner = BarcodeScanning.getClient()
            scanner.process(image)
                    .addOnSuccessListener { barcodes ->
                        barcodes.forEach {
                            listener.setResult(it)
                        }
                    }
                    .addOnFailureListener{
                        Log.e(TAG, "analyze: errorrrrr $it", it )
                    }
                    .addOnSuccessListener {
                        imageProxy.close()
                    }

        }
    }

    interface QRCodeListener {
        fun setResult(barcode: Barcode)
    }

    companion object {
        const val TAG = "ANALYSER"
    }
}


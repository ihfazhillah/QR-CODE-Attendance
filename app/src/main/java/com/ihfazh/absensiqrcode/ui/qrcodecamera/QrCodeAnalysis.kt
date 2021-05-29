package com.ihfazh.absensiqrcode.ui.qrcodecamera

import android.annotation.SuppressLint
import android.graphics.*
import android.media.Image
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.io.ByteArrayOutputStream

class QrCodeAnalysis(private val listener: QRCodeListener): ImageAnalysis.Analyzer {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy:  ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null){
            val image = InputImage.fromBitmap(croppedBitmap(mediaImage, imageProxy.cropRect), imageProxy.imageInfo.rotationDegrees)

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

    @SuppressLint("NewApi")
    private fun croppedBitmap(mediaImage: Image, cropRect: Rect): Bitmap {
        val yBuffer = mediaImage.planes[0].buffer // Y
        val vuBuffer = mediaImage.planes[2].buffer // VU

        val ySize = yBuffer.remaining()
        val vuSize = vuBuffer.remaining()

        val nv21 = ByteArray(ySize + vuSize)

        yBuffer.get(nv21, 0, ySize)
        vuBuffer.get(nv21, ySize, vuSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, mediaImage.width, mediaImage.height, null)
        val outputStream = ByteArrayOutputStream()
        yuvImage.compressToJpeg(cropRect, 100, outputStream)
        val imageBytes = outputStream.toByteArray()

        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    interface QRCodeListener {
        fun setResult(barcode: Barcode)
    }

    companion object {
        const val TAG = "ANALYSER"
    }
}


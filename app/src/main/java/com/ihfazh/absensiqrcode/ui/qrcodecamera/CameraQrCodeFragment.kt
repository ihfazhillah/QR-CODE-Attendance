package com.ihfazh.absensiqrcode.ui.qrcodecamera

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.mlkit.vision.barcode.Barcode
import com.ihfazh.absensiqrcode.databinding.FragmentCameraQrCodeBinding
import com.ihfazh.absensiqrcode.ui.detailevent.DetailEventViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraQrCodeFragment : Fragment() {

    private lateinit var cameraExecutor: ExecutorService
    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    private lateinit var imageCapture: ImageCapture
    private lateinit var binding: FragmentCameraQrCodeBinding
    private val viewModel: DetailEventViewModel by viewModels({requireParentFragment()})


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCameraQrCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("RestrictedApi", "NewApi", "UnsafeExperimentalUsageError",
        "UnsafeOptInUsageError"
    )
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener(
            {
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(binding.previewView.createSurfaceProvider())
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .setTargetResolution(Size(200, 200))
                    .build()

                imageAnalyzer.setAnalyzer(cameraExecutor, QrCodeAnalysis(object :
                    QrCodeAnalysis.QRCodeListener {
                    override fun setResult(barcode: Barcode) {
                        val value = barcode.rawValue
                        value?.let {
                           if (it.startsWith("attendanceqrcode")) {
                               val studentId = it.replace("attendanceqrcode.", "")
                               viewModel.setStudentId(studentId)
                           } else {
                               viewModel.setStudentId("")
                           }
                        } ?: viewModel.setStudentId("")
                    }
                }))

                val viewPort =
                    ViewPort.Builder(Rational(200, 200), binding.previewView.display.rotation)
                        .setScaleType(ViewPort.FIT_CENTER)
                        .build()


                val useCaseGroup = UseCaseGroup.Builder()
                    .addUseCase(preview)
                    .addUseCase(imageAnalyzer)
                    .setViewPort(viewPort)
                    .build()

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        viewLifecycleOwner, cameraSelector, useCaseGroup
                    )
//                    cameraProvider.bindToLifecycle(
//                            this, cameraSelector, preview, imageAnalyzer
//                    )
                } catch (e: Exception) {
                    Log.e(TAG, "Usecase binding failed", e)
                }

            },
            ContextCompat.getMainExecutor(requireContext())
        )
    }

    private fun allPermissionGranted(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                requireContext(),
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onResume() {
        super.onResume()
        if (allPermissionGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSION
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        teardownCamera()
    }

    @SuppressLint("RestrictedApi")
    private fun teardownCamera() {
        // https://github.com/android/camera-samples/issues/94
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.shutdown()
        }, ContextCompat.getMainExecutor(requireContext()))
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (allPermissionGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    requireActivity(),
                    "Permissions not granted by the user.",
                    Toast.LENGTH_LONG
                ).show()
                requireView().findNavController().navigateUp()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.studentDetail.observe(viewLifecycleOwner){
            if (it != null){
                binding.bottomSheet.result.text = "Student Found\n${it.firstName} ${it.lastName}"
            } else {
                binding.bottomSheet.result.text = "No Result"
            }
        }


//        if (allPermissionGranted()) {
//            startCamera()
//        } else {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                REQUIRED_PERMISSIONS,
//                REQUEST_CODE_PERMISSION
//            )
//        }


        cameraExecutor = Executors.newSingleThreadExecutor()

        with(binding.bottomSheet) {
            selesai.setOnClickListener {
                val action =
                    CameraQrCodeFragmentDirections.actionCameraQrCodeFragmentToHomeFragment()
                view.findNavController().navigate(action)

            }
        }
    }

    companion object {
        const val REQUEST_CODE_PERMISSION = 10
        const val TAG = "CAMERAQRCODE"
    }
}
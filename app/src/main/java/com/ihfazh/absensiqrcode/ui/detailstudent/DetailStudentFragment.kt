package com.ihfazh.absensiqrcode.ui.detailstudent

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ihfazh.absensiqrcode.databinding.FragmentDetailStudentBinding
import dagger.hilt.android.AndroidEntryPoint
import net.glxn.qrgen.android.QRCode
import java.util.*

@AndroidEntryPoint
class DetailStudentFragment : Fragment() {
    private lateinit var binding: FragmentDetailStudentBinding
    private val viewModel: DetailStudentViewModel by viewModels()
    private val args: DetailStudentFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            viewModel.getStudent(args.studentId).observe(viewLifecycleOwner){
                binding.qrcodeDetail.fullName.text = "${it.firstName} ${it.lastName}"
                binding.qrcodeDetail.uuid.text = it.studentId

                val code = "studentId#${it.studentId}"
                Glide.with(this@DetailStudentFragment)
                    .load(QRCode.from(code).bitmap())
                    .into(binding.qrcodeDetail.qrcode)

                (activity as AppCompatActivity).supportActionBar?.title = it.firstName
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
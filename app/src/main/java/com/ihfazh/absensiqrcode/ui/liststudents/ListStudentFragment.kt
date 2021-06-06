package com.ihfazh.absensiqrcode.ui.liststudents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihfazh.absensiqrcode.R
import com.ihfazh.absensiqrcode.databinding.FragmentListStudentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListStudentFragment : Fragment() {
    private lateinit var binding: FragmentListStudentBinding
    private val viewModel: ListStudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = ListStudentAdapter()
        with(binding.rvStudents){
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.getStudents().observe(viewLifecycleOwner){
            mAdapter.submitData(lifecycle, it)
        }


    }

    companion object {
            const val TAG = "List Student View"
    }
}
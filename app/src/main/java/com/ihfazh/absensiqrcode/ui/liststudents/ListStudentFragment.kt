package com.ihfazh.absensiqrcode.ui.liststudents

import Event.Student
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihfazh.absensiqrcode.R
import com.ihfazh.absensiqrcode.databinding.FragmentListStudentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListStudentFragment : Fragment(), ListStudentAdapter.OnStudentItemClicked {
    private lateinit var binding: FragmentListStudentBinding
    private val viewModel: ListStudentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListStudentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onItemClicked(student: Student) {
        val action =
            ListStudentFragmentDirections.actionListStudentFragment2ToDetailStudentFragment(student.studentId)
        requireView().findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = ListStudentAdapter().apply {
            clickListener = this@ListStudentFragment
        }
        with(binding.rvStudents) {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.getStudents().observe(viewLifecycleOwner) {
            mAdapter.submitData(lifecycle, it)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_users_menu, menu)
    }

    companion object {
        const val TAG = "List Student View"
    }
}
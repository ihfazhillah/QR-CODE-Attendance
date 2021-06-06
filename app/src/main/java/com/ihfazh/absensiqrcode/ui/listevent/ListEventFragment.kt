package com.ihfazh.absensiqrcode.ui.listevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ihfazh.absensiqrcode.databinding.FragmentListEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListEventFragment : Fragment() {
    private lateinit var binding: FragmentListEventBinding
    private val viewModel : ListEventViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListEventAdapter()
        with(binding.rvEvents){
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.list().observe(viewLifecycleOwner){
            adapter.submitData(lifecycle, it)
        }

    }

    companion object {
        const val TAG = "LIST EVENT"
    }
}
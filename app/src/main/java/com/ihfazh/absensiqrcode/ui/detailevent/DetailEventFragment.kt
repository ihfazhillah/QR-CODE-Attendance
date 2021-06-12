package com.ihfazh.absensiqrcode.ui.detailevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ihfazh.absensiqrcode.databinding.FragmentDetailEventBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailEventFragment : Fragment() {
    private lateinit var binding: FragmentDetailEventBinding
    private val viewModel: DetailEventViewModel by viewModels({requireParentFragment()})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEventBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventDetail.observe(viewLifecycleOwner){
            binding.eventTitle.text = it.title
            binding.eventDescription.text = it.description
            (requireActivity() as AppCompatActivity).supportActionBar?.title = it.title
        }
    }

    companion object {
        const val TAG = "Detail Event Fragment"
    }
}
package com.ihfazh.absensiqrcode.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ihfazh.absensiqrcode.R
import com.ihfazh.absensiqrcode.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCount().observe(viewLifecycleOwner){ count ->
                binding?.let{ homeBinding ->
                    val event = count.event
                    val student = count.student
                    if (event != null && student != null){
                        homeBinding.cardContent.subtitle.text = getString(R.string.subtitle_top_card, event, student)
                    }

                }
        }

        binding?.let {

//            it.cardContent.subtitle.text = getString(R.string.subtitle_top_card, 10, 20)

            it.cardContent.addButton.setOnClickListener {
                PopupMenu(requireContext().applicationContext, it).apply {
                    setOnMenuItemClickListener { menuItem ->
                        val id = menuItem.itemId
                        when (id) {
                            R.id.add_student -> {
                                val action =
                                        HomeFragmentDirections.actionHomeFragmentToAddStudentFragment()
                                view.findNavController().navigate(action)
                            }
                            R.id.add_event -> {
                                val action =
                                        HomeFragmentDirections.actionHomeFragmentToAddEventFragment()
                                view.findNavController().navigate(action)
                            }
                        }
                        true
                    }
                    inflate(R.menu.add_menu)
                    show()
                }
            }

            it.cardEvents.setOnClickListener{
                val direction = HomeFragmentDirections.actionHomeFragmentToListEventFragment()
                findNavController().navigate(direction)
            }

            it.cardStudents.setOnClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToListStudentFragment2()
                findNavController().navigate(direction)
            }

//            setupEventFragment(it)
        }
    }

//    private fun setupEventFragment(binding: FragmentHomeBinding) {
//        activity?.supportFragmentManager?.beginTransaction()
//                ?.add(binding.fragmentEvents.id, ListEventFragment())
//                ?.commit()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        setHasOptionsMenu(true)
        return binding?.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.main_menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        view?.let {
//            when (item.itemId) {
//                R.id.student_list -> it.findNavController()
//                    .navigate(HomeFragmentDirections.actionHomeFragmentToListStudentFragment2())
//                R.id.event_list -> it.findNavController()
//                    .navigate(HomeFragmentDirections.actionHomeFragmentToListEventFragment())
//            }
//
//        }
//        return true
//    }

    companion object {
        const val TAG = "HomeFragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
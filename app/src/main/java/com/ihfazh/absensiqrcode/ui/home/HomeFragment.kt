package com.ihfazh.absensiqrcode.ui.home

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.ihfazh.absensiqrcode.R
import com.ihfazh.absensiqrcode.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
//            it.btnAbsen.setOnClickListener {
//                val action = HomeFragmentDirections.actionHomeFragmentToCameraQrCodeFragment()
//                view.findNavController().navigate(action)
//            }
                it.fab.setOnClickListener {
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
        setHasOptionsMenu(true)
        return binding?.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        view?.let {
            when (item.itemId) {
                R.id.student_list -> it.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToListStudentFragment2())
                R.id.event_list -> it.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToListEventFragment())
            }

        }
        return true
    }

    companion object {
        const val TAG = "HomeFragment"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
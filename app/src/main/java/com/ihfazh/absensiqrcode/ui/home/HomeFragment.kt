package com.ihfazh.absensiqrcode.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu
import androidx.navigation.findNavController
import com.ihfazh.absensiqrcode.R
import com.ihfazh.absensiqrcode.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAbsen.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCameraQrCodeFragment()
            view.findNavController().navigate(action)
        }

        binding.fab.setOnClickListener{
            PopupMenu(this.context, it).apply {
                setOnMenuItemClickListener { menuItem ->
                    val id = menuItem.itemId
                    when(id){
                       R.id.add_student -> {
                           val action = HomeFragmentDirections.actionHomeFragmentToAddStudentFragment()
                           view.findNavController().navigate(action)
                       }
                        R.id.add_event -> {
                            val action = HomeFragmentDirections.actionHomeFragmentToAddEventFragment()
                            view.findNavController().navigate(action)
                        }
                    }
                    true
                }
                inflate(R.menu.add_menu)
                show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.student_list -> view?.findNavController()?.navigate(HomeFragmentDirections.actionHomeFragmentToListStudentFragment2())
        }
        return true
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}
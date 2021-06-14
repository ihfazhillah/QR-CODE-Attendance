package com.ihfazh.absensiqrcode.ui.detaileventcontainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ihfazh.absensiqrcode.databinding.FragmentDetailEventContainerBinding
import com.ihfazh.absensiqrcode.ui.detailevent.DetailEventViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailEventContainerFragment : Fragment() {
    /*
    This fragment handle swipe between event detail and scanner qr code
     */
    private lateinit var binding: FragmentDetailEventContainerBinding
    private val viewModel: DetailEventViewModel by viewModels()
    private val args: DetailEventContainerFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDetailEventContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setEventId(eventId = args.eventId)

        with(binding.viewPager){
            adapter = ViewPagerAdapter(this@DetailEventContainerFragment)
            reduceDragSensitifity()
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager){
            tab, position ->
                tab.text = if (position == 0){
                    "Detail"
                } else {
                    "Scan"
                }
        }.attach()
    }

    fun goToPage(page: Int){
        binding.viewPager.currentItem = page
    }

    companion object {
        const val TAG = "Detail Event Container"
    }
}

private fun ViewPager2.reduceDragSensitifity() {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop*8)       // "8" was obtained experimentally
}
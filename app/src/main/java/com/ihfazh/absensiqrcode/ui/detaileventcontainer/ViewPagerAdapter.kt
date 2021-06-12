package com.ihfazh.absensiqrcode.ui.detaileventcontainer

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ihfazh.absensiqrcode.ui.detailevent.DetailEventFragment
import com.ihfazh.absensiqrcode.ui.qrcodecamera.CameraQrCodeFragment

class ViewPagerAdapter(fa: Fragment): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0){
            DetailEventFragment()
        } else {
            CameraQrCodeFragment()
        }
    }
}
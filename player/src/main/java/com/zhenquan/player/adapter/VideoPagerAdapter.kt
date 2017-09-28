package com.zhenquan.player.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.zhenquan.player.ui.fragment.DefaultFragment


/**
 * ClassName:VideoPagerAdapter
 * Description:
 */
class VideoPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return DefaultFragment()
    }

    override fun getCount(): Int {
       return 3
    }
}
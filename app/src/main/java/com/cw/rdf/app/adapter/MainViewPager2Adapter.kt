package com.ecarx.ae.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Description:首页 ViewPager2 Adapter
 * @Author: wanglejun
 * @CreateDate： 2020/9/23 5:11 PM
 *
 */
class MainViewPager2Adapter(fragmentActivity: FragmentActivity, val fragments: List<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments.get(position)
    }
}
package com.cw.rdf.app

import androidx.fragment.app.Fragment
import com.cw.rdf.app.databinding.ActivityMainBinding
import com.cw.rdf.app.fragment.*
import com.cw.rdf.app.vm.MainVm
import com.cw.rdf.core.base.BaseBindingViewModelActivity
import com.ecarx.ae.adapter.MainViewPager2Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 *
 * @Description: sample code 首页
 * @Author: wanglejun
 * @CreateDate：2020/7/31 12:24 AM
 *
 */
class MainActivity : BaseBindingViewModelActivity<ActivityMainBinding, MainVm>() {

    override fun isLightStatusBar() = true

}
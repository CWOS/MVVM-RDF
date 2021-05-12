package com.cw.rdf.app.fragment

import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.cw.rdf.app.R
import com.cw.rdf.app.databinding.FragmentMainBinding
import com.cw.rdf.app.vm.MainVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.ecarx.ae.adapter.MainViewPager2Adapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/10/17 10:33 PM
 *
 */
class MainFragment:BaseBindingViewModelFragment<FragmentMainBinding,MainVm>() {
    private val fragments = arrayListOf<Fragment>()
    private lateinit var binding: FragmentMainBinding
    private val titles = arrayOf(
        R.string.index,
        R.string.article,
        R.string.project,
        R.string.mine
    )
    private lateinit var  tabLayoutMediator:TabLayoutMediator


    init {
        initFragments()
    }


    override fun initDataBinding(binding: FragmentMainBinding) {
        super.initDataBinding(binding)
        this.binding = binding
        binding.fragment = this

        activity?.let {
            binding.mainViewpager2.adapter = MainViewPager2Adapter(it, fragments)
        }
        binding.mainViewpager2.isUserInputEnabled = false
        tabLayoutMediator = TabLayoutMediator(
            binding.bottomTabLayout, binding.mainViewpager2,
            true
        ) { tab: TabLayout.Tab, position: Int ->
            run {
                tab.text = resources.getString(titles[position])
                when (position) {
                    0 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_index)
                    1 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_article)
                    2 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_project)
                    3 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_mine)
                }
            }

        }
        tabLayoutMediator.attach()
    }

    private fun initFragments() {
        fragments.add(IndexFragment())
        fragments.add(ArticleFragment())
        fragments.add(ProjectFragment())
        fragments.add(MineFragment())
    }
}
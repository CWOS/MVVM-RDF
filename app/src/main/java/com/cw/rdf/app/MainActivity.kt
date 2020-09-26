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

    private lateinit var binding: ActivityMainBinding
    private val fragments = arrayListOf<Fragment>()

    private val titles = arrayOf(
        R.string.index,
        R.string.article,
        R.string.project,
        R.string.todo,
        R.string.mine
    )

    override fun isLightStatusBar() = true
    override fun initDataBinding(binding: ActivityMainBinding) {
        super.initDataBinding(binding)
        this.binding = binding
        initFragments()
        viewModel.getWXArticleChapters()

        binding.mainViewpager2.adapter = MainViewPager2Adapter(this, fragments)
        binding.mainViewpager2.isUserInputEnabled = false
        val tabLayoutMediator = TabLayoutMediator(
            binding.bottomTabLayout, binding.mainViewpager2,
            true
        ) { tab: TabLayout.Tab, position: Int ->
            run {
                tab.text = resources.getString(titles[position])
                when (position) {
                    0 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_index)
                    1 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_article)
                    2 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_project)
                    3 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_todo)
                    4 -> tab.icon = resources.getDrawable(R.drawable.selector_tab_mine)
                }
            }

        }
        tabLayoutMediator.attach()
    }

    private fun initFragments() {
        fragments.add(IndexFragment())
        fragments.add(ArticleFragment())
        fragments.add(ProjectFragment())
        fragments.add(TodoFragment())
        fragments.add(MineFragment())
    }
}
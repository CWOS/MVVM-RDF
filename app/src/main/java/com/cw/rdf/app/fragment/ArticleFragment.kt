package com.cw.rdf.app.fragment

import androidx.lifecycle.Observer
import com.cw.rdf.app.databinding.FragmentArticleBinding
import com.cw.rdf.app.vm.ArticleVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment

/**
 * @Description:文章
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class ArticleFragment:BaseBindingViewModelFragment<FragmentArticleBinding,ArticleVm>() {
    override fun initDataBinding(binding: FragmentArticleBinding) {
        super.initDataBinding(binding)
        binding.lifecycleOwner = this
        viewModel.getMaidanArticleList()


        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        viewModel.articleList.observe(this, Observer {
            binding.swipeRefreshLayout.isRefreshing = false
        })

    }
}
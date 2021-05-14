package com.cw.rdf.app.fragment

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.cw.rdf.app.R
import com.cw.rdf.app.databinding.FragmentArticleBinding
import com.cw.rdf.app.model.Article
import com.cw.rdf.app.vm.ArticleVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment

/**
 * @Description:文章
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class ArticleFragment:BaseBindingViewModelFragment<FragmentArticleBinding,ArticleVm>() {
    private lateinit var articleBinding:FragmentArticleBinding
    override fun initDataBinding(binding: FragmentArticleBinding) {
        super.initDataBinding(binding)
        binding.lifecycleOwner = this
        binding.fragment = this
        viewModel.getMaidanArticleList()
        articleBinding = binding

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        viewModel.articleList.observe(this, Observer {
            binding.swipeRefreshLayout.isRefreshing = false
        })
    }

    fun onItemClick(item:Any){
        val bundle = Bundle()
        bundle.putSerializable("title",(item as Article).title)
        bundle.putSerializable("link",item.link)
        Navigation.findNavController(articleBinding.articleRecycler).navigate(R.id.action_mainFragment_to_articleDetailFragment,bundle)
    }
}
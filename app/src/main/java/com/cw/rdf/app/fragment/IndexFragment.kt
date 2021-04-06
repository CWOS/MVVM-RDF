package com.cw.rdf.app.fragment

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.cw.rdf.app.R
import com.cw.rdf.app.adapter.RecyclerHeadAndFootWrapper
import com.cw.rdf.app.adapter.RecyclerViewAdapter
import com.cw.rdf.app.databinding.FragmentIndexBinding
import com.cw.rdf.app.databinding.LayoutIndexRecyclerHeaderBinding
import com.cw.rdf.app.vm.IndexVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.base.SimpleBindingAdapter

/**
 * @Description:首页
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class IndexFragment : BaseBindingViewModelFragment<FragmentIndexBinding, IndexVm>() {
    private val TAG = "IndexFragment"
    private lateinit var binding: FragmentIndexBinding

        private lateinit var bannerAdapter: SimpleBindingAdapter
    @SuppressLint("WrongConstant")
    override fun initDataBinding(binding: FragmentIndexBinding) {
        super.initDataBinding(binding)
        this.binding = binding
        binding.lifecycleOwner = this

        viewModel.getBanners()
        viewModel.getArticle(1)

        bannerAdapter = SimpleBindingAdapter(R.layout.item_banner_index)
        val indexAdapter = SimpleBindingAdapter(R.layout.item_recycler_article)

        val headerViewBinding = DataBindingUtil.inflate<LayoutIndexRecyclerHeaderBinding>(
            LayoutInflater.from(context),
            R.layout.layout_index_recycler_header,
            binding.indexRecycler,
            false
        )
        headerViewBinding.vm = viewModel
        headerViewBinding.headerViewpager2.adapter = bannerAdapter


        viewModel.banners.observe(this, Observer {
            bannerAdapter.data = it
            val headAndFootWrapper =
                RecyclerHeadAndFootWrapper(indexAdapter as BaseBindingAdapter<Any, ViewDataBinding>)
            headAndFootWrapper.addHeaderView(headerViewBinding)

            binding.indexRecycler.adapter = headAndFootWrapper
        })

        viewModel.articleList.observe(this, Observer {
            indexAdapter.data = it
            binding.swipeRefreshLayout.isRefreshing = false
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getArticle(1)
        }
    }


}
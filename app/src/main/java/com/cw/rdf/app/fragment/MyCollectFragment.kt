package com.cw.rdf.app.fragment

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.cw.rdf.app.R
import com.cw.rdf.app.databinding.FragmentMyCollectBinding
import com.cw.rdf.app.databinding.LayoutFooterviewBinding
import com.cw.rdf.app.databinding.LayoutHeadviewBinding
import com.cw.rdf.app.vm.MyCollectVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.cw.rdf.recycle.adapter.RecyclerHeadAndFootWrapper
import com.cw.rdf.recycle.base.SimpleBindingAdapter

/**
 * @Description:我的收藏
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class MyCollectFragment:BaseBindingViewModelFragment<FragmentMyCollectBinding,MyCollectVm>() {
    override fun initDataBinding(binding: FragmentMyCollectBinding) {
        super.initDataBinding(binding)
        viewModel.getCollectList(0)

        val collectListAdapter = SimpleBindingAdapter(R.layout.item_recycler_article)
        val headerViewBinding = DataBindingUtil.inflate<LayoutHeadviewBinding>(
            LayoutInflater.from(context),
            R.layout.layout_headview,
            binding.collectListRecycler,
            false
        )

        val headerViewBinding1 = DataBindingUtil.inflate<LayoutHeadviewBinding>(
            LayoutInflater.from(context),
            R.layout.layout_headview,
            binding.collectListRecycler,
            false
        )

        val footerViewBinding = DataBindingUtil.inflate<LayoutFooterviewBinding>(
            LayoutInflater.from(context),
            R.layout.layout_footerview,
            binding.collectListRecycler,
            false
        )

        val footerViewBinding1 = DataBindingUtil.inflate<LayoutFooterviewBinding>(
            LayoutInflater.from(context),
            R.layout.layout_footerview,
            binding.collectListRecycler,
            false
        )



        viewModel.collectList.observe(this, Observer {
            collectListAdapter.data = it

            val headAndFootWrapper = RecyclerHeadAndFootWrapper(collectListAdapter)
            headAndFootWrapper.addHeaderView(headerViewBinding)
            headAndFootWrapper.addHeaderView(headerViewBinding1)
            headAndFootWrapper.addFooterView(footerViewBinding)
            headAndFootWrapper.addFooterView(footerViewBinding1)

            binding.collectListRecycler.adapter = headAndFootWrapper
        })
    }
}
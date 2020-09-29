package com.cw.rdf.app.fragment

import androidx.lifecycle.Observer
import com.cw.rdf.app.R
import com.cw.rdf.app.databinding.FragmentIndexBinding
import com.cw.rdf.app.di.vmModule
import com.cw.rdf.app.vm.IndexVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.cw.rdf.recycle.base.SimpleBindingAdapter

/**
 * @Description:首页
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class IndexFragment:BaseBindingViewModelFragment<FragmentIndexBinding,IndexVm>() {
    private lateinit var binding: FragmentIndexBinding
    private lateinit var adapter: SimpleBindingAdapter
    override fun initDataBinding(binding: FragmentIndexBinding) {
        super.initDataBinding(binding)
        this.binding = binding
        binding.lifecycleOwner = this
        viewModel.getBanners()
        viewModel.getArticle(1)
        adapter = SimpleBindingAdapter(R.layout.item_banner_index)
        viewModel.banners.observe(this, Observer {
            adapter.data = it
            binding.mainViewpager2.adapter = adapter
        })
    }
}
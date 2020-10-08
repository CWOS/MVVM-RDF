package com.cw.rdf.app.fragment

import android.annotation.SuppressLint
import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.cw.rdf.app.R
import com.cw.rdf.app.adapter.RecyclerHeadAndFootWrapper
import com.cw.rdf.app.databinding.FragmentIndexBinding
import com.cw.rdf.app.databinding.LayoutIndexRecyclerHeaderBinding
import com.cw.rdf.app.di.vmModule
import com.cw.rdf.app.vm.IndexVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.base.SimpleBindingAdapter
import kotlin.coroutines.Continuation

/**
 * @Description:首页
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class IndexFragment:BaseBindingViewModelFragment<FragmentIndexBinding,IndexVm>() {
    private  val TAG = "IndexFragment"
    private lateinit var binding: FragmentIndexBinding
    private lateinit var adapter: SimpleBindingAdapter
    @SuppressLint("WrongConstant")
    override fun initDataBinding(binding: FragmentIndexBinding) {
        super.initDataBinding(binding)
        this.binding = binding
        binding.lifecycleOwner = this
        viewModel.getBanners()
        viewModel.getArticle(1)

        adapter = SimpleBindingAdapter(R.layout.item_banner_index)

        val headerViewBinding = DataBindingUtil.inflate<LayoutIndexRecyclerHeaderBinding>(LayoutInflater.from(context),R.layout.layout_index_recycler_header,binding.indexRecycler,false)
        viewModel.banners.observe(this, Observer {
            adapter.data = it
            headerViewBinding.mainViewpager2.adapter = adapter
        })

        viewModel.articleList.observe(this, Observer {
            val indexAdapter = SimpleBindingAdapter(R.layout.item_recycler_index)
            indexAdapter.data = it
            val headAndFootWrapper = RecyclerHeadAndFootWrapper(indexAdapter as BaseBindingAdapter<Any, ViewDataBinding>)
            headAndFootWrapper.addHeaderView(R.layout.layout_index_recycler_header)

            binding.indexRecycler.adapter = headAndFootWrapper
        })
    }


}
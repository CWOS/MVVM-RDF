package com.cw.rdf.app.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.cw.rdf.app.R
import com.cw.rdf.recycle.adapter.RecyclerHeadAndFootWrapper
import com.cw.rdf.app.databinding.FragmentIndexBinding
import com.cw.rdf.app.databinding.LayoutIndexRecyclerHeaderBinding
import com.cw.rdf.app.model.Article
import com.cw.rdf.app.vm.IndexVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.base.SimpleBindingAdapter
import com.cw.rdf.recycle.paging.adapter.PagingHeadAndFootProxyAdapter
import com.cw.rdf.recycle.paging.adapter.SimplePageAdapter

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
        binding.fragment = this
        binding.lifecycleOwner = this

        viewModel.getBanners()
        viewModel.getArticle()

        bannerAdapter = SimpleBindingAdapter(R.layout.item_banner_index)
        val indexAdapter = PagingHeadAndFootProxyAdapter<Article>(R.layout.item_recycler_article)

        //head view
        val headerViewBinding = DataBindingUtil.inflate<LayoutIndexRecyclerHeaderBinding>(
            LayoutInflater.from(context),
            R.layout.layout_index_recycler_header,
            binding.indexRecycler,
            false
        )
        headerViewBinding.vm = viewModel
        headerViewBinding.headerViewpager2.adapter = bannerAdapter

        indexAdapter.addHeaderView(headerViewBinding)

        binding.indexRecycler.adapter = indexAdapter

        viewModel.banners.observe(this, Observer {
            bannerAdapter.data = it
        })

        viewModel.articleList.observe(this, Observer {
            indexAdapter.submitList(it)

            binding.swipeRefreshLayout.isRefreshing = false
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }
    }

    fun onItemClick(item:Any){
        val bundle = Bundle()
        bundle.putSerializable("title",(item as Article).title)
        bundle.putSerializable("link",item.link)
        Navigation.findNavController(binding.indexRecycler).navigate(R.id.action_mainFragment_to_articleDetailFragment,bundle)
    }
}
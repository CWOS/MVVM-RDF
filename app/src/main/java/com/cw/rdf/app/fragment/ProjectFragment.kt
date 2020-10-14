package com.cw.rdf.app.fragment

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.cw.rdf.app.R
import com.cw.rdf.app.databinding.FragmentProjectBinding
import com.cw.rdf.app.vm.ProjectVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.base.SimpleBindingAdapter

/**
 * @Description:项目
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class ProjectFragment : BaseBindingViewModelFragment<FragmentProjectBinding, ProjectVm>() {
    lateinit var adapter: SimpleBindingAdapter
    override fun initDataBinding(binding: FragmentProjectBinding) {
        super.initDataBinding(binding)
        viewModel.getProjectTree()
        adapter = SimpleBindingAdapter(R.layout.item_project_tab)
        binding.projectTreeRecycler.adapter = adapter
        viewModel.projectTreeList.observe(this, Observer {
            adapter.data = it
            adapter.itemEventHandler = viewModel
            adapter.itemClickListener =
                object : BaseBindingAdapter.OnItemClickListener<Any> {
                    override fun onItemClick(t: Any?, position: Int) {

                        viewModel.projectTreeList.value?.let {it2->
                            viewModel.getProjects(0, it2[position].id)
                            it2[position].isSelect = true
                        }
                        for (index in viewModel.projectTreeList.value!!.indices) {
                            if (index != position) {
                                viewModel.projectTreeList.value?.get(index)?.isSelect = false
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                }
        })

    }
}
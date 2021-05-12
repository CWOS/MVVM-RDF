package com.cw.rdf.app.fragment

import com.cw.rdf.app.databinding.FragmentMyCollectBinding
import com.cw.rdf.app.vm.MyCollectVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment

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
    }
}
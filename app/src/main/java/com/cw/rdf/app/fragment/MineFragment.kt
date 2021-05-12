package com.cw.rdf.app.fragment

import androidx.navigation.Navigation
import com.cw.rdf.app.R
import com.cw.rdf.app.databinding.FragmentMineBinding
import com.cw.rdf.app.vm.MineVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment

/**
 * @Description:我的
 * @Author: wanglejun
 * @CreateDate： 2020/9/24 12:05 AM
 *
 */
class MineFragment:BaseBindingViewModelFragment<FragmentMineBinding,MineVm>() {
    private val TAG = "MineFragment"
    private lateinit var binding: FragmentMineBinding
    override fun initDataBinding(binding: FragmentMineBinding) {
        super.initDataBinding(binding)
        this.binding = binding
        binding.fragment = this
    }

    fun navToLogin(){
        Navigation.findNavController(binding.userInfoLayout).navigate(R.id.action_mainFragment_to_loginFragment)
    }

    fun navToMyCollect(){
        Navigation.findNavController(binding.myCollectLayout).navigate(R.id.action_mainFragment_to_myCollectFragment)
    }

    fun logout(){
        viewModel.logout()
    }

    override fun isKeepViewModel(): Boolean {
        return true
    }
}
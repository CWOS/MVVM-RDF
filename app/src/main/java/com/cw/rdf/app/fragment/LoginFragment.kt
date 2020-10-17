package com.cw.rdf.app.fragment

import androidx.navigation.Navigation
import com.cw.rdf.app.databinding.FragmentLoginBinding
import com.cw.rdf.app.vm.LoginVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/10/17 11:00 PM
 *
 */
class LoginFragment:BaseBindingViewModelFragment<FragmentLoginBinding,LoginVm>() {
    private lateinit var binding: FragmentLoginBinding
    override fun initDataBinding(binding: FragmentLoginBinding) {
        super.initDataBinding(binding)
        this.binding = binding
        binding.fragment = this
    }
    fun back(){
        Navigation.findNavController(binding.closeImage).popBackStack()
    }
}
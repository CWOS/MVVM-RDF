package com.cw.rdf.app.fragment

import android.util.Log
import androidx.navigation.Navigation
import com.cw.rdf.app.EVENT_LOGIN_SUCCESS
import com.cw.rdf.app.databinding.FragmentLoginBinding
import com.cw.rdf.app.vm.MineVm
import com.cw.rdf.core.base.BaseBindingViewModelFragment
import com.cw.rdf.core.model.ViewModelEvent

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/10/17 11:00 PM
 *
 */
class LoginFragment:BaseBindingViewModelFragment<FragmentLoginBinding, MineVm>() {
    private val TAG = "LoginFragment"
    private lateinit var binding: FragmentLoginBinding
    override fun initDataBinding(binding: FragmentLoginBinding) {
        super.initDataBinding(binding)
        this.binding = binding
        binding.fragment = this
    }

    fun back(){
        Navigation.findNavController(binding.closeImage).popBackStack()
        binding.usernameInputview.contentText = ""
    }

    override fun isKeepViewModel(): Boolean {
        return true
    }


    override fun <T> onViewModelEvent(event: ViewModelEvent<T>) {
        event.getValueIfNotHandled()?.let {
            when(it){
                EVENT_LOGIN_SUCCESS->{
                    Navigation.findNavController(binding.closeImage).popBackStack()
                }
                else ->{

                }
            }
        }
    }
}
package com.cw.rdf.core.base

import androidx.databinding.ViewDataBinding
import com.cw.rdf.core.contract.OnVMEventListener

/**
 * @Description: Databinding + ViewModel BaseFragment
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 7:29 AM
 *
 */
class BaseBindingViewModelFragment<BINDING : ViewDataBinding, VM : BaseViewModel>:
    BaseBindingFragment<BINDING>(),OnVMEventListener {
    override fun initDataBinding(binding: BINDING) {
    }

    override fun onViewModelEvent(eventId: Int) {
    }
}
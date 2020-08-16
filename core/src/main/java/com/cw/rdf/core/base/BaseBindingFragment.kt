package com.cw.rdf.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.cw.rdf.core.utils.getBindingType

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/8/4 11:46 PM
 *
 */
abstract class BaseBindingFragment<BINDING:ViewDataBinding>:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = createDataBinding(inflater, container)
        binding.lifecycleOwner = this

        initDataBinding(binding)
        return binding.root
    }

    private fun createDataBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING {
        return getBindingType(javaClass)
            ?.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            ?.invoke(null, inflater, container, false) as BINDING
    }

    abstract fun initDataBinding(binding: BINDING)

}
package com.cw.rdf.core.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.cw.rdf.core.utils.getBindingType

/**
 * @Description: databinding BaseActivity
 * @Author: wanglejun
 * @CreateDate： 2020/8/3 11:40 PM
 *
 */
abstract class BaseBindingActivity<BINDING :ViewDataBinding>:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = createDataBinding()
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initDataBinding(binding)
    }

    private fun createDataBinding(): BINDING {
        return getBindingType(javaClass)
            ?.getMethod("inflate", LayoutInflater::class.java)
            ?.invoke(null, LayoutInflater.from(this)) as BINDING
    }

    abstract fun initDataBinding(binding: BINDING)
}
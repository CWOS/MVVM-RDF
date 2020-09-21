package com.cw.rdf.recycle.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

/**
 * @Description: 通用 page adapter 实现
 * @Author: wanglejun
 * @CreateDate： 2020/9/18 12:04 AM
 *
 */
class SimplePageAdapter(@param:LayoutRes @field:LayoutRes private val layoutRes: Int):BasePageAdapter<Any,ViewDataBinding>() {
    override fun getLayoutRes(): Int {
        return layoutRes
    }
}
package com.cw.rdf.recycle.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

/**
 * @Description: 通用 Binding Adapter 实现
 * @Author: wanglejun
 * @CreateDate： 2020/9/18 12:01 AM
 *
 */
class SimpleBindingAdapter(@param:LayoutRes @field:LayoutRes override val layoutRes: Int): BaseBindingAdapter<Any,ViewDataBinding>()
package com.cw.rdf.recycle.paging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.base.BindingLifecycleViewHolder

/**
 * @Description:支持 Pagging 的通用 Binding Adapter
 * @Author: wanglejun
 * @CreateDate： 2020/9/14 11:39 PM
 *
 */
abstract class BasePageAdapter<T,BINDING : ViewDataBinding> :
    PagedListAdapter<T, BindingLifecycleViewHolder<T, BINDING>>(
        CustomDiffItemCallback<T>()
    ) {


    //列表按下监听
    var itemOnClickListener: BaseBindingAdapter.OnItemClickListener<T>? = null
    var itemEventHandler: Any? = null

    /**
     *
     * @description 获取布局资源
     * @return 布局 id
     *
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingLifecycleViewHolder<T, BINDING> {
        val binding = DataBindingUtil.inflate<BINDING>(LayoutInflater.from(parent.context),getLayoutRes(),parent,false)
        val holder =
            BindingLifecycleViewHolder<T, BINDING>(
                binding
            )
        binding.lifecycleOwner = holder
        bindClick(holder,binding)
        return holder
    }

    override fun onBindViewHolder(holder: BindingLifecycleViewHolder<T, BINDING>, position: Int) {
        holder.bind(getItem(position))
        holder.setItemEventHandler(itemEventHandler)
    }

    /**
     *
     * @description 绑定 item 点击事件 回调
     * @param holder
     * @param binding
     * @return
     *
     */
    open fun bindClick(holder: BindingLifecycleViewHolder<*, *>, binding: BINDING){
        binding.root.setOnClickListener {
            val position = holder.layoutPosition
            itemOnClickListener?.onItemClick(getItem(position),position)
        }
    }

    override fun onViewAttachedToWindow(holder: BindingLifecycleViewHolder<T, BINDING>) {
        super.onViewAttachedToWindow(holder)
        holder.onAppear()
    }

    override fun onViewDetachedFromWindow(holder: BindingLifecycleViewHolder<T, BINDING>) {
        super.onViewDetachedFromWindow(holder)
        holder.onDisappear()
    }
}
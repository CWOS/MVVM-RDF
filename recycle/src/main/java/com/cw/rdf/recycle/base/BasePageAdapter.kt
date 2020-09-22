package com.cw.rdf.recycle.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager.widget.PagerAdapter

/**
 * @Description:支持 Pagging 的通用 Binding Adapter
 * @Author: wanglejun
 * @CreateDate： 2020/9/14 11:39 PM
 *
 */
abstract class BasePageAdapter<T,BINDING : ViewDataBinding> : PagedListAdapter<T,BindingViewHolder<T,BINDING>>(CustomDiffItemCallback<T>()) {


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
    ): BindingViewHolder<T, BINDING> {
        val binding = DataBindingUtil.inflate<BINDING>(LayoutInflater.from(parent.context),getLayoutRes(),parent,false)
        val holder = BindingViewHolder<T,BINDING>(binding)
        binding.lifecycleOwner = holder
        bindClick(holder,binding)
        return holder
    }

    override fun onBindViewHolder(holder: BindingViewHolder<T, BINDING>, position: Int) {
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
    protected fun bindClick(holder: BindingViewHolder<*, *>,binding: BINDING){
        binding.root.setOnClickListener {
            val position = holder.layoutPosition
            itemOnClickListener?.onItemClick(getItem(position),position)
        }
    }

    override fun onViewAttachedToWindow(holder: BindingViewHolder<T, BINDING>) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }

    override fun onViewDetachedFromWindow(holder: BindingViewHolder<T, BINDING>) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetah()
    }

    /**
     * 比较器
     * @param T
     */
    class CustomDiffItemCallback<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem === newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

    }
}
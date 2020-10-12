package com.cw.rdf.recycle.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.cw.rdf.recycle.listener.ObserverListChangeListener

/**
 * @Description:通用 Base Binding RecyclerView Adapter
 * @Author: wanglejun
 * @CreateDate： 2020/9/15 9:44 AM
 *
 */
abstract class BaseBindingAdapter<T : Any, BINDING : ViewDataBinding>() :
    RecyclerView.Adapter<BindingViewHolder<T, BINDING>>() {

    //item 按下监听
    var itemClickListener: OnItemClickListener<T>? = null

    //item 长按监听
    var itemLongClickListener: OnItemLongClickListener<T>? = null

    //observer 数据变化监听
    private var observabListChanageListener: ObserverListChangeListener<T>? = null

    //ViewType 构建器
    var itemViewTypeCreator: ItemViewTypeCreator? = null
    var itemEventHandler: Any? = null

    var data: List<T>? = null
        set(data) {
            field = data
            notifyDataSetChanged()

            //如果是ObservableList则为其添加changeCallback
            if (data is ObservableList<*>) {
                if (observabListChanageListener == null) {
                    observabListChanageListener = ObserverListChangeListener(this)
                }
                (data as ObservableList<T>).removeOnListChangedCallback(observabListChanageListener)
                data.addOnListChangedCallback(observabListChanageListener)
            }
        }

    @get:LayoutRes
    abstract val layoutRes: Int

    fun getItem(position: Int): T? {
        return data?.getOrNull(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingViewHolder<T, BINDING> {
        val layout = itemViewTypeCreator?.getItemLayout(viewType) ?: layoutRes
        val binding = DataBindingUtil.inflate<BINDING>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        val holder = BindingViewHolder<T, BINDING>(binding)
        binding.lifecycleOwner = holder
        bindClick(holder, binding)
        return holder
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: BindingViewHolder<T, BINDING>, position: Int) {
        holder.bind(getItem(position))
        holder.setItemEventHandler(itemEventHandler)
    }

    override fun getItemViewType(position: Int): Int {
        return itemViewTypeCreator?.getItemViewType(position, getItem(position))
            ?: super.getItemViewType(position)
    }


    /**
     *
     * @description 绑定 item 点击事件 回调
     * @param holder
     * @param binding
     * @return
     *
     */
     open fun bindClick(holder: BindingViewHolder<*, *>, binding: BINDING) {
        binding.root.setOnClickListener {
            val position = holder.layoutPosition
            itemClickListener?.onItemClick(getItem(position), position)
        }

        binding.root.setOnLongClickListener{
            val position = holder.layoutPosition
            itemLongClickListener?.onItemLongClick(getItem(position), position)
            return@setOnLongClickListener true
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
     *
     * @Description:item 按下监听
     *
     */
    interface OnItemClickListener<T> {
        fun onItemClick(t: T?, position: Int)
    }

    /**
     *
     * @Description:item 长按监听
     * @Author: wanglejun
     * @CreateDate：2020/10/12 4:36 PM
     *
     */
    interface OnItemLongClickListener<T> {
        fun onItemLongClick(t: T?, position: Int)
    }

    /**
     *
     * @Description: ViewType 构建器 ，多种布局类型 RecycleView 实现此接口
     *
     */
    interface ItemViewTypeCreator {
        fun getItemViewType(position: Int, item: Any?): Int
        fun getItemLayout(viewType: Int): Int
    }
}
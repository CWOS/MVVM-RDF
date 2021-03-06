package com.cw.rdf.recycle.paging.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.collection.SparseArrayCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.cw.rdf.recycle.base.BindingLifecycleViewHolder

/**
 * @Description:支持添加 Header 和 Footer 的 PagedListAdapter，
 * 参考 https://juejin.cn/post/6844903814189826062
 * @Author: wanglejun
 * @CreateDate： 5/7/21 11:12 PM
 *
 */
class PagingHeadAndFootProxyAdapter<T : Any>(@param:LayoutRes @field:LayoutRes private val layoutRes: Int):BasePageAdapter<T,ViewDataBinding>() {

    private val TAG = "PagingHeadAndFootProxyA"
    private val BASE_ITEM_TYPE_HEADER = 100000
    private val BASE_ITEM_TYPE_FOOTER = 200000
    private val headerViews: SparseArrayCompat<ViewDataBinding> = SparseArrayCompat()
    private val footViews: SparseArrayCompat<ViewDataBinding> = SparseArrayCompat()

    override fun getLayoutRes(): Int {
        return layoutRes
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingLifecycleViewHolder<T, ViewDataBinding> {
        if (headerViews.get(viewType) != null) {
            return BindingLifecycleViewHolder(headerViews.get(viewType)!!)
        }

        if (footViews.get(viewType) != null) {
            return BindingLifecycleViewHolder(footViews.get(viewType)!!)
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingLifecycleViewHolder<T, ViewDataBinding>, position: Int) {
        if (isHeaderViewPos(position)) {
            return
        }
        if (isFooterViewPos(position)) {
            return
        }
        super.onBindViewHolder(holder, position - getHeadersCount())
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer,getHeadersCount()))
    }

    override fun getItemCount(): Int {
        return getHeadersCount() + getFootersCount() + getRealItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderViewPos(position)) {
            return headerViews.keyAt(position)
        } else if (isFooterViewPos(position)) {
            return footViews.keyAt(position - getHeadersCount() - getRealItemCount())
        }
        return super.getItemViewType(position)
    }


    private fun getRealItemCount(): Int {
        return super.getItemCount()
    }

    private fun getHeadersCount(): Int {
        return headerViews.size()
    }

    private fun getFootersCount(): Int {
        return footViews.size()
    }

    private fun isHeaderViewPos(position: Int): Boolean {
        return position < getHeadersCount()
    }

    private fun isFooterViewPos(position: Int): Boolean {
        if (getFootersCount() == 0) {
            return false
        }
        return position > getHeadersCount() + getRealItemCount() - 1
    }

    fun addHeaderView(headerViewBinding: ViewDataBinding) {
        headerViews.put(BASE_ITEM_TYPE_HEADER + headerViews.size(), headerViewBinding)
        notifyItemChanged(0)
    }

    fun addFooterView(footViewBinding: ViewDataBinding) {
        footViews.put(BASE_ITEM_TYPE_FOOTER + footViews.size(), footViewBinding)
    }

    /**
     *
     * @description 绑定 item 点击事件 回调
     * @param holder
     * @param binding
     * @return
     *
     */
    override fun bindClick(holder: BindingLifecycleViewHolder<*, *>, binding: ViewDataBinding){
        binding.root.setOnClickListener {
            val position = holder.layoutPosition - getHeadersCount()
            itemOnClickListener?.onItemClick(getItem(position),position)
        }
    }
}
package com.cw.rdf.app.adapter

import android.util.Log
import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.base.BindingLifecycleViewHolder


/**
 * @Description: RecyclerView 添加 Header Foot View Adapter装饰器，参考鸿神baseAdapter
 * @Author: wanglejun
 * @CreateDate： 2020/10/7 11:29 PM
 *
 */
class RecyclerHeadAndFootWrapper<T : Any, BINDING : ViewDataBinding>(val innerAdapter: BaseBindingAdapter<T, BINDING>) :
    RecyclerView.Adapter<BindingLifecycleViewHolder<T, BINDING>>() {
    private val TAG = "RecyclerHeadAndFootWrap"
    private val BASE_ITEM_TYPE_HEADER = 100000
    private val BASE_ITEM_TYPE_FOOTER = 200000
    private val headerViews: SparseArrayCompat<BINDING> = SparseArrayCompat()
    private val footViews: SparseArrayCompat<BINDING> = SparseArrayCompat()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingLifecycleViewHolder<T, BINDING> {

        if (headerViews.get(viewType) != null) {
            return BindingLifecycleViewHolder(headerViews.get(viewType)!!)
        }

        if (footViews.get(viewType) != null) {
            return BindingLifecycleViewHolder(footViews.get(viewType)!!)
        }
        return innerAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: BindingLifecycleViewHolder<T, BINDING>, position: Int) {

        if (isHeaderViewPos(position)) {
            return
        }
        if (isFooterViewPos(position)) {
            return
        }
        innerAdapter.onBindViewHolder(holder, position - getHeadersCount())
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
        return innerAdapter.getItemViewType(position)
    }

    private fun getRealItemCount(): Int {
        return innerAdapter.itemCount
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
        return position > getFootersCount() + getRealItemCount()
    }

    fun addHeaderView(headerViewBinding: BINDING) {
        headerViews.put(BASE_ITEM_TYPE_HEADER + headerViews.size(), headerViewBinding)
        notifyItemChanged(0)
    }

    fun addFooterView(footViewBinding: BINDING) {
        footViews.put(BASE_ITEM_TYPE_FOOTER + footViews.size(), footViewBinding)
    }

    override fun onViewAttachedToWindow(holder: BindingLifecycleViewHolder<T, BINDING>) {
        super.onViewAttachedToWindow(holder)
        Log.d(TAG,"onViewAttachedToWindow......${holder}")
        innerAdapter.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: BindingLifecycleViewHolder<T, BINDING>) {
        super.onViewDetachedFromWindow(holder)
        Log.d(TAG,"onViewDetachedFromWindow......${holder}")
        innerAdapter.onViewDetachedFromWindow(holder)
    }
}
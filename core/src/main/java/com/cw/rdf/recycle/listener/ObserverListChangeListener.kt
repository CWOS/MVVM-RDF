package com.cw.rdf.recycle.listener

import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/9/16 11:41 PM
 *
 */
class ObserverListChangeListener<T>(val adapter: RecyclerView.Adapter<*>):ObservableList.OnListChangedCallback<ObservableList<T>>() {
    override fun onChanged(sender: ObservableList<T>?) {
        adapter.notifyDataSetChanged()
    }

    override fun onItemRangeRemoved(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter.notifyItemRangeRemoved(positionStart,itemCount)
    }

    override fun onItemRangeMoved(
        sender: ObservableList<T>?,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int
    ) {
        adapter.notifyItemMoved(fromPosition,itemCount)
    }

    override fun onItemRangeInserted(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {
        adapter.notifyItemRangeInserted(positionStart,itemCount)
    }

    override fun onItemRangeChanged(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {
       adapter.notifyItemRangeChanged(positionStart,itemCount)
    }
}
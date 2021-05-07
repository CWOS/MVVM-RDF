package com.cw.rdf.recycle.paging.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 5/7/21 11:23 PM
 *
 */
class AdapterDataObserverProxy(val adapterDataObserver: RecyclerView.AdapterDataObserver,val headerCount:Int): RecyclerView.AdapterDataObserver() {
    override fun onChanged() {
        adapterDataObserver.onChanged()
    }


    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeChanged(positionStart + headerCount,itemCount)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        adapterDataObserver.onItemRangeChanged(positionStart + headerCount,itemCount,payload)
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeInserted(positionStart + headerCount,itemCount)
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeRemoved(positionStart + headerCount,itemCount)
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeMoved(fromPosition + headerCount,toPosition + headerCount,itemCount)
    }
}
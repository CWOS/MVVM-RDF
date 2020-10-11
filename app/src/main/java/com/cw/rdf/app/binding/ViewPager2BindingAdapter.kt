package com.cw.rdf.app.binding

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.base.SimpleBindingAdapter

/**
 * @Description: ViewPager2 BindingAdapter
 * @Author: wanglejun
 * @CreateDate： 2020/10/11 11:33 PM
 *
 */

@BindingAdapter("data", "itemLayout")
fun setData(viewpager2: ViewPager2, data: List<Any>?, @LayoutRes itemLayout: Int) {
    setData(viewpager2, data, itemLayout, null, null)
}

@BindingAdapter("data", "itemLayout", "itemClick")
fun setData(
    viewpager2: ViewPager2,
    data: List<Any>?,
    @LayoutRes itemLayout: Int,
    itemClick: BaseBindingAdapter.OnItemClickListener<Any>
) {
    setData(viewpager2, data, itemLayout, itemClick, null)
}

@BindingAdapter("data", "itemLayout", "itemEventHandler")
fun setData(
    viewpager2: ViewPager2,
    data: List<Any>?,
    @LayoutRes itemLayout: Int,
    itemEventHandler: Any
) {
    setData(viewpager2, data, itemLayout, null, itemEventHandler)
}


@BindingAdapter("data", "itemLayout", "itemClick", "itemEventHandler")
fun setData(
    viewpager2: ViewPager2,
    data: List<Any>?,
    @LayoutRes itemLayout: Int,
    itemClick: BaseBindingAdapter.OnItemClickListener<Any>?,
    itemEventHandler: Any?
) {
    val adapter = viewpager2.adapter
    if (adapter == null) {
        val simpleAdapter = SimpleBindingAdapter(itemLayout)
        simpleAdapter.data = data
        simpleAdapter.itemClickListener = itemClick
        simpleAdapter.itemEventHandler = itemEventHandler
        viewpager2.adapter = simpleAdapter
    } else if (adapter is BaseBindingAdapter<*, *>) {
        (adapter as BaseBindingAdapter<Any, ViewDataBinding>).data = data
        adapter.itemClickListener = itemClick
        adapter.itemEventHandler = itemEventHandler
    }
}
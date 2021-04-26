package com.cw.rdf.recycle.binding

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.base.BasePageAdapter
import com.cw.rdf.recycle.base.SimpleBindingAdapter
import com.cw.rdf.recycle.base.SimplePageAdapter

/**
 * @Description: ReccyclerView BindingAdapter
 * @Author: wanglejun
 * @CreateDate： 2020/9/18 7:48 AM
 *
 */

/**************** RecyclerView BaseBindingAdapter 相关属性设置 Start *************************/

/**
 *
 * @description 设置 RecyclerView 数据源
 * @param recyclerView RecyclerView 对象
 * @param data RecyclerView 数据源
 * @return
 *
 */
@BindingAdapter("data")
fun setData(recyclerView: RecyclerView,data: List<Any>?){
    val adapter = recyclerView.adapter
    if (adapter is BaseBindingAdapter<*,*>) {
        (adapter as BaseBindingAdapter<Any, ViewDataBinding>).data = data
    }
}

/**
 *
 * @description 使用默认 SimpleBindingAdapter
 * @param recyclerView RecyclerView 对象
 * @param itemLayout 布局 ID
 * @return
 *
 */
@BindingAdapter("itemLayout")
fun setItemLayout(recyclerView: RecyclerView,@LayoutRes itemLayout:Int){
    val simpleAdapter = SimpleBindingAdapter(itemLayout)
    recyclerView.adapter = simpleAdapter
}

/**
 *
 * @description 设置 RecyclerView Item 点击事件
 * @param recyclerView RecyclerView 对象
 * @param itemClick RecyclerView 点击事件
 * @return
 *
 */
@BindingAdapter("itemClick")
fun setItemClick(recyclerView: RecyclerView,itemClick: BaseBindingAdapter.OnItemClickListener<Any>){
    val adapter = recyclerView.adapter
    if (adapter is BaseBindingAdapter<*,*>) {
        (adapter as BaseBindingAdapter<Any, ViewDataBinding>).itemClickListener = itemClick
    }
}

/**
 *
 * @description 设置 RecyclerView 数据源和 Item 点击事件
 * @param recyclerView RecyclerView 对象
 * @param data 数据源
 * @param itemClick Item 点击事件
 * @return
 *
 */
@BindingAdapter("data","itemClick")
fun setData(recyclerView: RecyclerView, data: List<Any>?, itemClick: BaseBindingAdapter.OnItemClickListener<Any>){
    val adapter = recyclerView.adapter
    if (adapter is BaseBindingAdapter<*,*>) {
        (adapter  as BaseBindingAdapter<Any, ViewDataBinding>).data = data
        adapter.itemClickListener = itemClick
    }
}

@BindingAdapter("data","itemLayout")
fun setData(recyclerView: RecyclerView, data: List<Any>?, @LayoutRes itemLayout: Int){
    setData(recyclerView,data,itemLayout,null,null,null)
}

@BindingAdapter("data","itemLayout","itemClick")
fun setData(recyclerView: RecyclerView, data: List<Any>?, @LayoutRes itemLayout: Int,itemClick: BaseBindingAdapter.OnItemClickListener<Any>){
    setData(recyclerView,data,itemLayout,itemClick,null,null)
}

@BindingAdapter("data","itemLayout","itemViewType")
fun setData(recyclerView: RecyclerView, data: List<Any>?, @LayoutRes itemLayout: Int,itemViewType: BaseBindingAdapter.ItemViewTypeCreator){
    setData(recyclerView,data,itemLayout,null,itemViewType,null)
}

@BindingAdapter("data","itemLayout","itemEventHandler")
fun setData(recyclerView: RecyclerView, data: List<Any>?, @LayoutRes itemLayout: Int,itemEventHandler: Any){
    setData(recyclerView,data,itemLayout,null,null,itemEventHandler)
}

@BindingAdapter("data","itemLayout","itemClick","itemViewType")
fun setData(recyclerView: RecyclerView, data: List<Any>?, @LayoutRes itemLayout: Int, itemClick: BaseBindingAdapter.OnItemClickListener<Any>, itemViewType: BaseBindingAdapter.ItemViewTypeCreator){
    setData(recyclerView,data,itemLayout,itemClick,itemViewType,null)
}

@BindingAdapter("data","itemLayout","itemClick","itemEventHandler")
fun setData(recyclerView: RecyclerView, data: List<Any>?, @LayoutRes itemLayout: Int, itemClick: BaseBindingAdapter.OnItemClickListener<Any>, itemEventHandler: Any){
    setData(recyclerView,data,itemLayout,itemClick,null,itemEventHandler)
}

@BindingAdapter("data","itemLayout","itemViewType","itemEventHandler")
fun setData(recyclerView: RecyclerView, data: List<Any>?, @LayoutRes itemLayout: Int,itemViewType: BaseBindingAdapter.ItemViewTypeCreator,itemEventHandler: Any){
    setData(recyclerView,data,itemLayout,null,itemViewType,itemEventHandler)
}

/**
 *
 * @description 设置 RecyclerView 数据源、布局、Item 点击事件、itemViewType、itemEventHandler
 * @param recyclerView RecyclerView 对象
 * @param data 数据源
 * @param itemLayout Item 布局 ID
 * @param itemClick Item 点击事件
 * @param itemViewType Item View Type
 * @param itemEventHandler Item Event Handler
 * @return
 *
 */
@BindingAdapter("data","itemLayout","itemClick","itemViewType","itemEventHandler")
fun setData(recyclerView: RecyclerView,data: List<Any>?,@LayoutRes itemLayout: Int
            ,itemClick: BaseBindingAdapter.OnItemClickListener<Any>?
            ,itemViewType: BaseBindingAdapter.ItemViewTypeCreator?
            ,itemEventHandler: Any? ){
    val adapter = recyclerView.adapter
    if(adapter == null){
        val simpleAdapter = SimpleBindingAdapter(itemLayout)
        simpleAdapter.data = data
        simpleAdapter.itemClickListener = itemClick
        simpleAdapter.itemViewTypeCreator = itemViewType
        simpleAdapter.itemEventHandler = itemEventHandler
        recyclerView.adapter = simpleAdapter
    } else if(adapter is BaseBindingAdapter<*,*>){
        (adapter as BaseBindingAdapter<Any,ViewDataBinding>).data = data
        adapter.itemClickListener = itemClick
        adapter.itemViewTypeCreator = itemViewType
        adapter.itemEventHandler = itemEventHandler
    }
}

/**************** RecyclerView BaseBindingAdapter 相关属性设置 End *************************/



/**************** RecyclerView 相关属性设置 Start *************************/

/**
 * 
 * @description 是否开启嵌套滑动
 * @param scroll 是否滑动
 * @return 
 * 
 */
@BindingAdapter("limitScroll")
fun setLimitScroll(recyclerView: RecyclerView,scroll:Boolean){
    recyclerView.isNestedScrollingEnabled = scroll
}

/**
 *
 * @description RecyclerView 滑动到指定位置
 * @param position 指定位置
 * @return
 *
 */
@BindingAdapter("scrollPosition")
fun setScrollPosition(recyclerView: RecyclerView,position: Int){
    recyclerView.scrollToPosition(position)
}

/**
 *
 * @description RecyclerView 设置竖直方向的分割线
 * @param dividerSize 分割线大小
 * @return
 *
 */
@BindingAdapter("verticalSpace")
fun setVerticalSpace(recyclerView: RecyclerView,dividerSize: Float){
    val itemVDecoration = DividerItemDecoration(recyclerView.context, LinearLayout.VERTICAL)
    val drawable = GradientDrawable()
    drawable.setSize(0, dividerSize.toInt())
    drawable.setColor(Color.TRANSPARENT)
    itemVDecoration.setDrawable(drawable)
    recyclerView.addItemDecoration(itemVDecoration)
}

/**
 *
 * @description RecyclerView 设置水平方向的分割线
 * @param dividerSize 分割线大小
 * @return
 *
 */
@BindingAdapter("horizontalSpace")
fun setHorizontalSpace(recyclerView: RecyclerView,dividerSize: Float){
    val itemVDecoration = DividerItemDecoration(recyclerView.context, LinearLayout.HORIZONTAL)
    val drawable = GradientDrawable()
    drawable.setSize(dividerSize.toInt(), 0)
    drawable.setColor(Color.TRANSPARENT)
    itemVDecoration.setDrawable(drawable)
    recyclerView.addItemDecoration(itemVDecoration)
}


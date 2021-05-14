package com.cw.rdf.recycle.binding

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.cw.rdf.recycle.base.BaseBindingAdapter
import com.cw.rdf.recycle.paging.adapter.BasePageAdapter
import com.cw.rdf.recycle.paging.adapter.SimplePageAdapter

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 4/18/21 11:57 PM
 *
 */
/**************** RecyclerView BasePageAdapter 相关属性设置 Start *************************/

/**
 *
 * @description 设置 RecyclerView PagerAdapter 数据源、Item 布局，Item 点击事件，Item Event Handler
 * @param recyclerView RecyclerView 对象
 * @param pageData 数据源
 * @param pageItemLayout Item 布局 ID
 * @param itemClick Item 点击事件
 * @param itemEventHandler Item Event Handler
 * @return
 *
 */
@BindingAdapter("pageData","pageItemLayout","pageItemClick","itemEventHandler")
fun <T> setPageData(recyclerView: RecyclerView, pageData: PagedList<T>?, @LayoutRes pageItemLayout:Int, itemClick: BaseBindingAdapter.OnItemClickListener<T>?, itemEventHandler: Any?){
    val adapter = recyclerView.adapter
    if(adapter == null){
        val simplePageAdapter =
            SimplePageAdapter<T>(pageItemLayout)
        simplePageAdapter.submitList(pageData)
        simplePageAdapter.itemOnClickListener = itemClick
        simplePageAdapter.itemEventHandler = itemEventHandler
        recyclerView.adapter = simplePageAdapter
    }else if(adapter is SimplePageAdapter<*>){
        (adapter as SimplePageAdapter<T>).submitList(pageData)
        adapter.itemOnClickListener = itemClick
        adapter.itemEventHandler = itemEventHandler
    }
}

@BindingAdapter("pageData","pageItemLayout")
fun <T> setPageData(recyclerView: RecyclerView, pageData: PagedList<T>?, @LayoutRes pageItemLayout: Int){
    setPageData(recyclerView,pageData,pageItemLayout,null,null)
}

@BindingAdapter("pageData","pageItemLayout","pageItemClick")
fun <T> setPageData(recyclerView: RecyclerView, pageData: PagedList<T>?, @LayoutRes pageItemLayout: Int, itemClick: BaseBindingAdapter.OnItemClickListener<T>){
    setPageData(recyclerView,pageData,pageItemLayout,itemClick, null)
}

@BindingAdapter("pageData","pageItemLayout","itemEventHandler")
fun <T> setPageData(recyclerView: RecyclerView, pageData: PagedList<T>?, @LayoutRes pageItemLayout: Int, itemEventHandler: Any){
    setPageData(recyclerView,pageData,pageItemLayout,null,itemEventHandler)
}


@BindingAdapter("pageItemClick")
fun <T> setItemClick(recyclerView: RecyclerView,itemClick: BaseBindingAdapter.OnItemClickListener<T>?){
    val adapter = recyclerView.adapter
    if(adapter is BasePageAdapter<*,*>){
        (adapter as BasePageAdapter<T, ViewDataBinding>).itemOnClickListener = itemClick
    }
}
/**************** RecyclerView BasePageAdapter 相关属性设置 End *************************/

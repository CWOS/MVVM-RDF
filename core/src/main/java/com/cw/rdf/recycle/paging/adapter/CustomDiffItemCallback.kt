package com.cw.rdf.recycle.paging.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * @Description: PagedListAdapter 比较器
 * @Author: wanglejun
 * @CreateDate： 5/7/21 11:17 PM
 *
 */
class CustomDiffItemCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem === newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem

}
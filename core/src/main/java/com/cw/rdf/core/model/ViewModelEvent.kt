package com.cw.rdf.core.model

/**
 * @Description: ViewModle 事件 Modle
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 6:27 AM
 *
 */
class ViewModelEvent<T>(private val value: T) {

    private var hasBeanHandled = false

    fun getValueIfNotHandled(): T? {
        return if (hasBeanHandled) {
            null
        } else {
            hasBeanHandled = true
            value
        }
    }

    fun get(): T {
        return value
    }
}
//页面返回事件
const val EVENT_BACK = 0xff
//点击事件
const val EVENT_CLICK = 0x100
//列表 item 点击事件
const val EVENT_ITEM_CLICK = 0x101
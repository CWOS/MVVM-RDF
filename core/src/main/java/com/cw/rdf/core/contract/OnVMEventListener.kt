package com.cw.rdf.core.contract

import com.cw.rdf.core.model.ViewModelEvent

/**
 * @Description: 用于 ViewModel 和 Activity/Fragment 之间事件传递回调
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 6:23 AM
 *
 */
interface OnVMEventListener {
    /**
     *
     * @description ViewModel 事件响应
     * @param eventId 事件 id，根据实际业务自定义
     * @return
     *
     */
    fun <T> onViewModelEvent(event:ViewModelEvent<T>)
}
package com.cw.rdf.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cw.rdf.core.http.CoroutineLambda
import com.cw.rdf.core.http.ErrorHandle
import com.cw.rdf.core.http.request
import com.cw.rdf.core.model.EVENT_BACK
import com.cw.rdf.core.model.ViewModelEvent
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat

/**
 * @Description: ViewModel 基类，定义数据加载状态（isLoading）、提示信息（hintText/hintTextRes）、
 * ViewModel 与视图之间的事件传递 ViewModelEvent
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 6:09 AM
 *
 */
open class BaseViewModel: ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var hintText = MutableLiveData<ViewModelEvent<String>>()
    var hintTextRes = MutableLiveData<ViewModelEvent<Int>>()

    var event = MutableLiveData<ViewModelEvent<Int>>()


    fun getHintRes(): Int {
        return hintTextRes.value?.getValueIfNotHandled() ?: 0
    }

    fun getEventId(): Int {
        return event.value?.get()?:-1
    }

    fun getHintText(): String? {
        return hintText.value?.getValueIfNotHandled()
    }

    protected fun postHintText(msg: String) {
        hintText.value = ViewModelEvent(msg)
    }

    protected fun postHintText(msgRes: Int) {
        hintTextRes.value = ViewModelEvent(msgRes)
    }

    protected fun postEvent(eventId: Int) {
        event.value = ViewModelEvent(eventId)
    }

    fun launch(isShowLoading: Boolean = true, onError: ErrorHandle ? = null, block: CoroutineLambda) {
        request(error = { t ->
            isLoading.value = false
            onError?.invoke(t) == true
        }) {
            isLoading.value = isShowLoading
            block()
            isLoading.value = false
        }
    }

    fun back(){
        postEvent(EVENT_BACK)
    }
}
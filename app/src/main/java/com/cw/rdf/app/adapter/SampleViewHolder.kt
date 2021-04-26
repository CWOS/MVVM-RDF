package com.cw.rdf.app.adapter

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.cw.rdf.recycle.base.BindingLifecycleViewHolder

/**
 * @Description: BindingLifecycleViewHolder 使用示例
 * @Author: wanglejun
 * @CreateDate： 4/14/21 10:15 PM
 *
 */
class SampleViewHolder<T,BINDING : ViewDataBinding>(override val binding: BINDING):BindingLifecycleViewHolder<T,BINDING>(binding) {
    private val TAG = "SampleViewHolder"
    private val sampleObserver = SampleObserver()
    init {
        lifecycle.addObserver(sampleObserver)
    }

    fun bindData(t:T?){
        super.bind(t)
        sampleObserver.data = t
    }

    inner class SampleObserver : LifecycleObserver {
        var data : T ?=null

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onAppear() {
            //TODO onAppear do something
            Log.d(TAG, "appear: $data")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onDisappear() {
            //TODO onDisappear do something
            Log.d(TAG, "disappear: $data")
        }
    }
}
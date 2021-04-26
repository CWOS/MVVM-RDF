package com.cw.rdf.recycle.base

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.cw.rdf.core.BR

/**
 * @Description:自定义 Binding Lifecycle ViewHolder
 * @Author: wanglejun
 * @CreateDate： 2020/9/14 11:54 PM
 *
 */
open class BindingLifecycleViewHolder<T,BINDING : ViewDataBinding>(open val binding: BINDING):RecyclerView.ViewHolder(binding.root) , LifecycleOwner{
    private val TAG = "BindingViewHolder"
    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }

    fun bind(t:T?){
        binding.setVariable(BR.item,t)
    }

    fun setItemEventHandler(handler:Any?){
        binding.setVariable(BR.handler,handler)
    }


    fun onAppear(){
        Log.d(TAG,"onAppear......${binding}")
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun onDisappear(){
        Log.d(TAG,"onDisappear......${binding}.")
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }
}
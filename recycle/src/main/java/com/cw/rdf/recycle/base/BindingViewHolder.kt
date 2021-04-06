package com.cw.rdf.recycle.base

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.cw.rdf.recycle.BR

/**
 * @Description:自定义 Binding ViewHolder
 * @Author: wanglejun
 * @CreateDate： 2020/9/14 11:54 PM
 *
 */
class BindingViewHolder<T,BINDING : ViewDataBinding>(val binding: BINDING):RecyclerView.ViewHolder(binding.root) , LifecycleOwner{
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


    fun onAttach(){
        Log.d("wang","onAttach......${binding}")
//        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun onDetah(){
        Log.d("wang","onDetah......${binding}.")
//        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }
}
package com.cw.rdf.core.base

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.cw.rdf.core.BR
import com.cw.rdf.core.contract.OnVMEventListener
import com.cw.rdf.core.ext.bind
import com.cw.rdf.core.model.ViewModelEvent
import com.cw.rdf.core.utils.getViewModelType
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ViewModelOwnerDefinition
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.koin.getViewModel

/**
 * @Description: Databinding + ViewModel BaseFragment
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 7:29 AM
 *
 */
open class BaseBindingViewModelFragment<BINDING : ViewDataBinding, VM : BaseViewModel>:
    BaseBindingFragment<BINDING>(),OnVMEventListener {
    val viewModel:VM by lazy {
        createViewModel()
    }

    override fun initDataBinding(binding: BINDING) {
        //RDF 默认自动绑定 vm。具体业务实现中在实际的视图 xml 文件中声明当前视图的 ViewModel 为
        // vm 即可自动进行绑定。
        binding.setVariable(BR.vm,viewModel)
    }

    /**
     *
     * @description 初始化 ViewModel 并自动进行绑定
     * @param
     * @return VM
     *
     */
    private fun createViewModel():VM{
        val viewModel = getViewModelType(javaClass)?.let {
            //通过反射获取具体的 ViewModel 类实例，使用 koin 动态注入
//            getKoin().getViewModel(this,it.kotlin,null,null) as VM
            var owner: (() -> ViewModelOwner)?
            if (isKeepViewModel()){
                owner ={
                    ViewModelOwner.Companion.from(this.activity!!,activity)
                }
                return@let this.getViewModel(null,null,owner,it.kotlin,null) as VM
            }
            owner ={
                ViewModelOwner.Companion.from(this,this)
            }
            this.getViewModel(null,null,owner,it.kotlin,null) as VM
        }
        viewModel?.bind(this)
        return viewModel!!
    }

    override fun <T> onViewModelEvent(event: ViewModelEvent<T>) {
    }


    /**
     *
     * @description 是否保持 ViewModel。默认创建与当前 Fragment 生命周期绑定的 ViewModel。
     * 重写此方法返回 true，则创建与当前 Fragment 宿主 Activity 生命周期绑定的 ViewModel，与当前
     * Activity 绑定的其他 Fragment 可共享该 ViewMoel
     * @return true：保持 ViewModel 生命周期与宿主 Activity 同步，fasle：保持 ViewModel 与当前
     * Fragment 生命周期同步
     *
     */
    open fun isKeepViewModel():Boolean{
        return false
    }
}
package com.cw.rdf.core.base

import androidx.databinding.ViewDataBinding
import com.cw.rdf.core.BR
import com.cw.rdf.core.contract.OnVMEventListener
import com.cw.rdf.core.ext.bind
import com.cw.rdf.core.model.EVENT_BACK
import com.cw.rdf.core.model.ViewModelEvent
import com.cw.rdf.core.utils.getViewModelType
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.ViewModelOwnerDefinition
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.qualifier.named

/**
 * @Description: Databinding + ViewModel BaseActivity
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 7:09 AM
 *
 */
open class BaseBindingViewModelActivity<BINDING : ViewDataBinding, VM : BaseViewModel>:
    BaseBindingActivity<BINDING>(),OnVMEventListener {



    val viewModel:VM by lazy {
        createViewModel()
    }

    override fun initDataBinding(binding: BINDING) {
        //RDF 默认自动绑定 vm。具体业务实现中在实际的视图 xml 文件中声明当前视图的 ViewModel 为
        // vm 即可自动进行绑定。
        binding.setVariable(BR.vm,viewModel)

    }

    override fun <T> onViewModelEvent(event: ViewModelEvent<T>) {
        event.getValueIfNotHandled()?.let {
            if(it == EVENT_BACK){
                onBackPressed()
            }
        }

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
            val owner:ViewModelOwnerDefinition={
                ViewModelOwner.Companion.from(this,this)
            }
            this.getViewModel(null,null,owner,it.kotlin,null) as VM
        }
        viewModel?.bind(this)
        return viewModel!!
    }
}
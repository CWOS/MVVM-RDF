package com.cw.rdf.core.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import com.cw.rdf.core.BR
import com.cw.rdf.core.contract.OnVMEventListener
import com.cw.rdf.core.ext.bind
import com.cw.rdf.core.model.EVENT_BACK
import com.cw.rdf.core.utils.getViewModelType
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @Description: Databinding + ViewModel BaseActivity
 * @Author: wanglejun
 * @CreateDate： 2020/8/13 7:09 AM
 *
 */
class BaseBindingViewModelActivity<BINDING : ViewDataBinding, VM : BaseViewModel>:
    BaseBindingActivity<BINDING>(),OnVMEventListener{

    val viewModel:VM by lazy {
        createViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initDataBinding(binding: BINDING) {
        //RDF 默认自动绑定 vm。具体业务实现中在实际的视图 xml 文件中声明当前视图的 ViewModel 为
        // vm 即可自动进行绑定。
        binding.setVariable(BR.vm,viewModel)
    }

    override fun onViewModelEvent(eventId: Int) {
        if(eventId == EVENT_BACK){
            onBackPressed()
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
            viewModel<BaseViewModel>() as VM
        }
        viewModel?.bind(this)
        return viewModel!!
    }
}
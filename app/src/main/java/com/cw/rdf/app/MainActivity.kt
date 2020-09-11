package com.cw.rdf.app

import android.os.Bundle
import com.cw.rdf.app.databinding.ActivityMainBinding
import com.cw.rdf.app.vm.MainVm
import com.cw.rdf.core.base.BaseActivity
import com.cw.rdf.core.base.BaseBindingViewModelActivity

/**
 *
 * @Description: sample code 首页
 * @Author: wanglejun
 * @CreateDate：2020/7/31 12:24 AM
 *
 */
class MainActivity : BaseBindingViewModelActivity<ActivityMainBinding,MainVm>() {

    override fun isLightStatusBar() = true
    override fun initDataBinding(binding: ActivityMainBinding) {
        super.initDataBinding(binding)
        viewModel.getWXArticleChapters()
    }
}
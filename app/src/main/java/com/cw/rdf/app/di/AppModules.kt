package com.cw.rdf.app.di

import com.cw.rdf.app.vm.MainVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/8/19 7:35 AM
 *
 */
val appViewModelModules = module {
    viewModel {
        MainVm()
    }
}
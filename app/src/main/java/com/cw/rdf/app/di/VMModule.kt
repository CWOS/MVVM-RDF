package com.cw.rdf.app.di

import com.cw.rdf.app.vm.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/9/11 7:59 AM
 *
 */
val vmModule = module {
    viewModel {
        MainVm(get())
    }
    viewModel {
        IndexVm(get())
    }
    viewModel {
        ArticleVm(get())
    }
    viewModel {
        ProjectVm(get())
    }
    viewModel {
        MyCollectVm(get())
    }
    viewModel {
        MineVm(get(),get())
    }

}
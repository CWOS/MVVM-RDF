package com.cw.rdf.app.di

import com.cqrd.mrt.mcf.di.NAME_BASE_URL
import com.cqrd.mrt.mcf.di.NAME_DEBUG
import com.cw.rdf.app.vm.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
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
        IndexVm()
    }
    viewModel {
        ClassifyVm()
    }
    viewModel {
        ProjectVm()
    }
    viewModel {
        ToDoVm()
    }
    viewModel {
        MineVm()
    }
}
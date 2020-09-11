package com.cw.rdf.app.di

import com.cqrd.mrt.mcf.di.NAME_BASE_URL
import com.cqrd.mrt.mcf.di.NAME_DEBUG
import com.cw.rdf.app.http.ApiService
import com.cw.rdf.app.repository.DataRepository
import com.cw.rdf.app.vm.MainVm
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/8/19 7:35 AM
 *
 */
val appModule = module {
    single(named(NAME_BASE_URL)) {
        "https://www.wanandroid.com"
    }
    single(named(NAME_DEBUG)) {
        true
    }

    single { provideApiService(get()) }
    single { provideDataRepository(get()) }
}

fun provideApiService(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)

fun provideDataRepository(apiService: ApiService):DataRepository = DataRepository(apiService)
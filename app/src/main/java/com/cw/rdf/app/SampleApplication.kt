package com.cw.rdf.app

import android.app.Application
import com.cqrd.mrt.mcf.di.retrofitModule
import com.cw.rdf.app.di.appModule
import com.cw.rdf.app.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 2020/8/19 7:38 AM
 *
 */
class SampleApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@SampleApplication)
            modules(retrofitModule, appModule, vmModule)
        }
    }
}
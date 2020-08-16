package com.cw.rdf.core

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.cw.rdf.core.di.viewModelModules
import com.cw.rdf.core.ext.getApplicationByReflect
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @Description:自动完成初始化，初始化时机比 Application#onCreate 还早，
 * 思路来自 AndroidAutoSize 框架
 * @Author: wanglejun
 * @CreateDate： 2020/8/17 6:07 AM
 *
 */
class InitProvider:ContentProvider() {

    override fun onCreate(): Boolean {
        var application = context?.applicationContext
        if(application == null){
            application = getApplicationByReflect()
        }
        //初始化 koin
        startKoin {
            androidLogger(Level.DEBUG)
            application?.let { androidContext(it) }
            modules(viewModelModules)
        }
        return true
    }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}
package com.cw.rdf.recycle.paging

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope

/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 4/26/21 10:50 PM
 *
 */
class PageKeyedDataSourceFactory<T>(private val block: suspend CoroutineScope.(pageNum:Int, pageSize:Int) -> List<T>):DataSource.Factory<Int,T>() {

    private var dataSource :GeneralPageKeyedDataSource<T> ?= null

    override fun create(): DataSource<Int, T> {
        //isInvalid 时需重建 DataSource
        if(dataSource == null || dataSource?.isInvalid!!){
            dataSource = GeneralPageKeyedDataSource(block)
        }
        return dataSource as GeneralPageKeyedDataSource<T>
    }

    fun invalidate(){
        dataSource?.invalidate()
    }
}
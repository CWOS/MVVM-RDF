package com.cw.rdf.recycle.paging

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope


/**
 * @Description:
 * @Author: wanglejun
 * @CreateDate： 4/20/21 11:08 PM
 *
 */
class PositionalDataSourceFactory<T>(val block:suspend CoroutineScope.(pageInde:Int)->List<T>): DataSource.Factory<Int,T>() {
   private var dataSource:GeneralPositionalDataSource<T>?=null
    override fun create(): DataSource<Int, T> {
        if(dataSource == null || dataSource?.isInvalid!!){
            dataSource = GeneralPositionalDataSource(block)
        }
        return dataSource as GeneralPositionalDataSource<T>
    }
}
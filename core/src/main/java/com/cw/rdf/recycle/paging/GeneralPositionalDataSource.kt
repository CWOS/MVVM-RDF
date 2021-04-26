package com.cw.rdf.recycle.paging

import androidx.paging.PositionalDataSource
import com.cw.rdf.core.http.ErrorHandle
import com.cw.rdf.core.http.request
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException

/**
 * @Description:通用 PositionalDataSource
 * @Author: wanglejun
 * @CreateDate： 4/20/21 11:13 PM
 *
 */
class GeneralPositionalDataSource<T>(private val block:suspend CoroutineScope.(pageIndex:Int)->List<T>): PositionalDataSource<T>(){
    private val onError: ErrorHandle = {
        it is HttpException && it.code() == 404
    }
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        request(error = onError) {
            val bean = block(params.startPosition)
            callback.onResult(bean)
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
       request(error = onError) {
           //params.requestedStartPosition = 0
           val bean = block(params.requestedStartPosition)
           callback.onResult(bean,0)
       }
    }
}
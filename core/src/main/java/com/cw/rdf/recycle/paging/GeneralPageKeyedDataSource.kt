package com.cw.rdf.recycle.paging

import androidx.paging.PageKeyedDataSource
import com.cw.rdf.core.http.ErrorHandle
import com.cw.rdf.core.http.request
import kotlinx.coroutines.CoroutineScope
import retrofit2.HttpException

/**
 * @Description:通用 PageKeyedDataSource
 * @Author: wanglejun
 * @CreateDate： 4/26/21 12:08 AM
 *
 */
class GeneralPageKeyedDataSource<T>(private val block: suspend CoroutineScope.(pageNum:Int, pageSize:Int) -> List<T>):PageKeyedDataSource<Int,T>() {
    private val onError: ErrorHandle = {
        it is HttpException && it.code() == 404
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>) {
        request(error = onError) {
            val bean = block(0,params.requestedLoadSize)
            callback.onResult(bean,null,1)
        }
    }

    //加载下一页
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        request(error = onError) {
            val bean = block(params.key,params.requestedLoadSize)
            callback.onResult(bean,params.key+1)
        }
    }

    //加载上一页
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        request(error = onError) {
            val bean = block(params.key,params.requestedLoadSize)
            callback.onResult(bean,params.key-1)
        }    }
}
package com.cw.rdf.core.http

import kotlinx.coroutines.*

/**
 *Author: chengminghui
 *Time: 2019-12-03
 *Description: xxx
 */

typealias ErrorHandle = (Throwable) -> Boolean
typealias CoroutineLambda = suspend CoroutineScope.() -> Unit


private fun request(block: CoroutineLambda) {
    val exceptionHandle = CoroutineExceptionHandler { coroutineContext, throwable ->
        throwable.printStackTrace()
    }
    GlobalScope.launch(exceptionHandle) {
        withContext(Dispatchers.Main) {
            block()
        }
    }


}

fun request(exceptionHandler: HttpExceptionHandler? = RetrofitConfig.httpExceptionHandler,
            error: ErrorHandle? = null,
            block: CoroutineLambda) {
    request {
        try {
            block()
        } catch (e: Exception) {
            e.printStackTrace()
            if (error?.invoke(e) != true) {
                exceptionHandler?.handleException(e)
            }
        }
    }
}

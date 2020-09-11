package com.cw.rdf.core.http

/**
 *Author: chengminghui
 *Time: 2019-09-06
 *Description: xxx
 */


interface HttpExceptionHandler {
    fun handleException(throwable: Throwable)
}
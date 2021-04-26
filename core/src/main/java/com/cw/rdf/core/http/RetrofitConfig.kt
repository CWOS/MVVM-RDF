package com.cw.rdf.core.http

import com.google.gson.Gson
import okhttp3.CookieJar

/**
 *Author: chengminghui
 *Time: 2020-02-21
 *Description: xxx
 */

object RetrofitConfig {
    var cookieJar: CookieJar? = null
    var gson: Gson? = null
    var httpExceptionHandler: com.cw.rdf.core.http.HttpExceptionHandler? = null
    var connectTimeout: Long? = null
    var callTimeout: Long? = null
    var readTimeout: Long? = null
    var writeTimeout: Long? = null
}


fun retrofitConfig(block: RetrofitConfig.() -> Unit) {
    block(RetrofitConfig)
}
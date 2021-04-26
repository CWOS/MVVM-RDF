package com.cw.rdf.core.http

import android.content.Context
import android.net.ParseException
import com.cw.rdf.core.R
import com.google.gson.JsonParseException
import org.jetbrains.anko.toast
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import javax.net.ssl.SSLHandshakeException

/**
 *Author: chengminghui
 *Time: 2020-02-21
 *Description: xxx
 */
class DefaultHttpExceptionHandler(private val context: Context) :
    HttpExceptionHandler {

    override fun handleException(throwable: Throwable) {
        when (throwable) {
            //http 异常
            is HttpException -> context.toast(R.string.http_server_exception)

            //连接异常
            is ConnectException,
            is UnknownHostException,
            is UnknownServiceException,
            is SocketTimeoutException -> context.toast(R.string.http_connect_exception)

            //数据解析异常
            is JsonParseException,
            is JSONException,
            is ParseException -> context.toast(R.string.http_data_exception)

            //证书异常
            is SSLHandshakeException -> context.toast(R.string.http_ssl_exception)

            //其他异常
            else -> context.toast(R.string.http_net_exception)
        }
    }
}
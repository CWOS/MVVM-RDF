package com.cw.rdf.core.http.cookie

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 *
 * @Description: 实现 CookieJar 接口，托管给 CookieStore 实现
 * @Author: wanglejun
 * @CreateDate：2020/10/24 2:55 PM
 *
 */
class HttpCookieJar(private val cookieStore: CookieStore) : CookieJar {

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore.addCookie(url,cookies)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore.getCookies(url)
    }
}

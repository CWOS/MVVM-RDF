package com.cw.rdf.core.http.cookie

import okhttp3.Cookie
import okhttp3.HttpUrl

/**
 * @Description: 本地缓存 Cookie 接口，自定义 Cookie 缓存策略实现 CookieStore 接口即可
 * @Author: wanglejun
 * @CreateDate： 2020/10/24 2:27 PM
 *
 */
interface CookieStore {
    /**
     * 
     * @description 添加httpurl对应请求接口返回 cookie
     * @param httpUrl 请求接口 URL
     * @param cookie 当前接口返回 cookie
     * @return 
     * 
     */
    fun addCookie(httpUrl:HttpUrl,cookie: Cookie)

    /**
     *
     * @description 添加httpurl对应请求接口返回 cookie 集合
     * @param httpUrl 请求接口 URL
     * @param cookie 当前接口返回 cookie
     * @return
     *
     */
    fun addCookie(httpUrl: HttpUrl,cookies:List<Cookie>)


    /**
     *
     * @description 获取本地缓存所有 Cookie 集合
     * @return cookie 集合
     *
     */
    fun getCookies():List<Cookie>

    /**
     *
     * @description 获取httpurl对应接口本地缓存 cookie 集合
     * @param httpUrl 请求接口 URL
     * @return cookie 集合
     *
     */
    fun getCookies(httpUrl: HttpUrl):List<Cookie>


    /**
     *
     * @description 移除本地所有 Cookie
     * @return 是否移除成功
     *
     */
    fun removeAll():Boolean

    /**
     *
     * @description 移除指定 httpUrl 对应的本地缓存 Cookie
     * @param httpUrl 请求接口 URL
     * @return 是否移除成功
     *
     */
    fun remove(httpUrl: HttpUrl,cookie: Cookie):Boolean
}
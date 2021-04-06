package com.cw.rdf.core.http.cookie

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.cw.rdf.core.ext.clear
import com.cw.rdf.core.ext.getSharedPreference
import com.cw.rdf.core.ext.put
import com.cw.rdf.core.ext.remove
import okhttp3.Cookie
import okhttp3.HttpUrl
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.set

/**
 * @Description:默认 CookieStore 实现
 * @Author: wanglejun
 * @CreateDate： 2020/10/27 6:16 PM
 *
 */
class DefaultCookieStoreImpl(val context: Context) : CookieStore {
    private val TAG = "DefaultCookieStoreImpl"
    private val PREFS_COOKIE = "CookiePrefs"
    private val PREFIX_HOST_NAME = "host_"
    private val PREFIX_COOKIE_NAME = "cookie_"
    private var cookies: HashMap<String, ConcurrentHashMap<String, Cookie>> = HashMap()
    private var cookiePrefs: SharedPreferences

    init {
        cookiePrefs = context.getSharedPreference(PREFS_COOKIE)
        //取出本地缓存 Cookie 到内存
        getCookieForLocal()
        //清除过期 Cookie
        clearInvalidCookie()
    }

    override fun addCookie(httpUrl: HttpUrl, cookie: Cookie) {
        val name = this.cookieName(cookie)
        val hostKey = this.hostName(httpUrl)

        if (!cookies.containsKey(hostKey)) {
            hostKey?.let { cookies.put(it, ConcurrentHashMap()) }
        }

        name?.let { cookies[hostKey]?.put(it, cookie) }

        //保存 httpurl 对应的所有 cookie 的 keys
        hostKey?.let { updateKeysOfHostkey(it) }
        //保存 cookie
        name?.let {
            cookiePrefs.put(PREFIX_COOKIE_NAME + it, SerializableCookie().encode(cookie))
        }

    }

    override fun addCookie(httpUrl: HttpUrl, cookies: List<Cookie>) {
        for (cookie in cookies) {
            if (isCookieExpired(cookie)) {
                removeCookieForPrefs(hostName(httpUrl), cookie)
                continue
            }
            this.addCookie(httpUrl, cookie)
        }
    }

    override fun getCookies(): List<Cookie> {
        val cookieList = arrayListOf<Cookie>()
        for (hostKey in cookies.keys) {
            cookieList.addAll(getCookiesForMemory(hostKey))
        }
        return cookieList
    }

    override fun getCookies(httpUrl: HttpUrl): List<Cookie> {
        return getCookiesForMemory(hostName(httpUrl))
    }

    override fun removeAll(): Boolean {
        cookiePrefs.clear()
        return true
    }

    override fun remove(httpUrl: HttpUrl,cookie: Cookie): Boolean {
        return removeCookieForPrefs(hostName(httpUrl),cookie)
    }


    /**
     *
     * @description 从内存中获取 hostKey 对应的 cookie 集合
     * @param hostKey hostKey 值
     * @return 符合条件的 cookie 集合
     *
     */
    private fun  getCookiesForMemory(hostKey: String): List<Cookie> {
        if (!cookies.containsKey(hostKey)) {
            return emptyList()
        }
        val cookieList = arrayListOf<Cookie>()
        for (cookie in cookies[hostKey]?.values!!) {
            if (isCookieExpired(cookie)) {
                removeCookieForPrefs(hostKey, cookie)
            } else {
                cookieList.add(cookie)
            }
        }
        return cookieList
    }

    /**
     *
     * @description 从本地缓存中删除 hostKey 对应的 cookie 集合中的 cookie
     * @param hostKey hostKey 值
     * @param cookie 需要删除掉 cookie
     * @return 是否删除成功
     *
     */
    private fun removeCookieForPrefs(hostKey: String, cookie: Cookie):Boolean {
        val name = cookieName(cookie)
        if (cookies.containsKey(hostKey) && cookies[hostKey]!!.containsKey(name)) {
            //移除内存中对应的 cookie
            cookies[hostKey]?.remove(name)
            //移除本地缓存的 cookie
            cookiePrefs.remove(PREFIX_COOKIE_NAME + name)
            //更新本地缓存的 hostKey 对应的 cookie 的 keys
            updateKeysOfHostkey(hostKey)
            return true
        }
        return false
    }

    /**
     *
     * @description 从本地缓存中取出 Cookie 到内存
     *
     */
    private fun getCookieForLocal() {
        val localCookies = HashMap(cookiePrefs.all)
        for (key in localCookies.keys) {
            if (!key.contains(PREFIX_HOST_NAME)) {
                continue
            }
            val cookieName = localCookies[key] as String
            if (cookieName.isEmpty()) {
                continue
            }
            cookies.let {
                if (!it.containsKey(key)) {
                    it[key] = ConcurrentHashMap()
                }
            }

            val cookieNames = cookieName.split(",")
            for (name in cookieNames) {
                val encodeCookie =
                    cookiePrefs.getString(PREFIX_COOKIE_NAME + name, null) ?: continue
                val decodeCookie = SerializableCookie().decode(encodeCookie)
                decodeCookie?.let {
                    cookies[key]?.put(name, it)
                }
            }
        }
        localCookies.clear()
    }

    /**
     *
     * @description 清除失效 Cookie
     *
     */
    private fun clearInvalidCookie() {
        for (hostKey in cookies.keys) {
            var changeFlag = false
            for (entry in cookies[hostKey]!!.entries) {
                val key = entry.key
                val cookie = entry.value
                if (isCookieExpired(cookie)) {
                    cookies[hostKey]?.remove(key)
                    cookiePrefs.remove(PREFIX_COOKIE_NAME + key)
                    changeFlag = true
                }
            }
            //清除失效 cookie 后更新本地缓存的 hostKey 对应的 cookie 的 keys
            if (changeFlag) {
                updateKeysOfHostkey(hostKey)
            }
        }
    }

    /**
     *
     * @description 更新本地缓存的 hostkey 对应的 cookie 的 keys
     * @param hostkey hostKey 值
     *
     */
    private fun updateKeysOfHostkey(hostKey: String) {
        cookiePrefs.put(hostKey, TextUtils.join(",", cookies[hostKey]!!.keys))
    }

    /**
     *
     * @description 判断当前 cookie 是否失效
     * @param cookie 当前cookie
     * @return 是否失效
     *
     */
    private fun isCookieExpired(cookie: Cookie): Boolean {
        return cookie.expiresAt() < System.currentTimeMillis()
    }


    private fun hostName(httpUrl: HttpUrl): String {
        return if (httpUrl.host()
                .startsWith(PREFIX_HOST_NAME)
        ) httpUrl.host() else PREFIX_HOST_NAME + httpUrl.host()
    }

    private fun cookieName(cookie: Cookie?): String? {
        return if (cookie == null) null else cookie.name() + cookie.domain()
    }
}